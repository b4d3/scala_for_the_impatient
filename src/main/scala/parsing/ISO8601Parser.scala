package parsing

import java.time.LocalDateTime

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers


/**
  * 4. Write a parser that can parse date and time expressions in ISO 8601. Your parser should
  * return a java.time.LocalDateTime object.
  */
class ISO8601Parser extends RegexParsers {

  /**
    * ISO 8601 DateTime format
    *
    * 2017-11-20T17:44:37+00:00
    * 2017-11-20T17:44:37Z
    * 20171120T174437Z
    */
  val yyyy: Regex = "[0-9]{4}".r
  val MM: Regex = "0[1-9]|1[0-2]".r
  val dd: Regex = "[0-2][0-9]|3[0-1]".r
  val hh: Regex = "0[0-9]|1[0-2]|2[0-4]".r
  val mm: Regex = "[0-5][0-9]".r
  val ss: Regex = "[0-5][0-9]".r

  //  def expr: Parser[LocalDateTime] = (year ~ month ~ day | year ~ "-" ~> month ~ "-" ~> day) <~ "T" ~ ((hour ~ min ~ sec <~ "Z") | (hour ~ ":" ~>  min ~ ":" ~> sec ~ ("Z" | "+" ~> hour ~ ":" ~>  min ~ ":" ~> sec)) ^^ {
  //    case year ~ month ~ day ~ hour ~ min ~ sec ~ tz => LocalDateTime.of(year, month, day, hour, min, sec)
  //  }

  def expr: Parser[LocalDateTime] = year ~ month ~ day ~ hour ~ min ~ sec ~ opt(tz) ^^ {
    case year ~ month ~ day ~ hour ~ min ~ sec ~ Some(tz) => LocalDateTime.of(year, month, day, hour, min, sec).plusHours(tz._1).plusMinutes(tz._2)
    case year ~ month ~ day ~ hour ~ min ~ sec ~ None => LocalDateTime.of(year, month, day, hour, min, sec)
  }

  def year: Parser[Int] = yyyy ^^ {
    _.toInt
  }

  def month: Parser[Int] = opt("-") ~> MM ^^ {
    _.toInt
  }

  def day: Parser[Int] = "-" ~> dd <~ "T" ^^ {
    _.toInt
  }

  def hour: Parser[Int] = hh ^^ {
    _.toInt
  }

  def min: Parser[Int] = opt(":") ~> mm ^^ {
    _.toInt
  }

  def sec: Parser[Int] = ":" ~> ss <~ "Z" ^^ {
    _.toInt
  }

  def tz: Parser[(Int, Int)] = "+" ~> hour ~ min ^^ { case h ~ m => (h, m) }
}
