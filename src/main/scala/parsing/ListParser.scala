package parsing

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

/**
  * 3. Write a parser that parses a list of integers (such as (1, 23, -79)) into a List[Int].
  */
class ListParser extends RegexParsers {

  /**
    * expr ::= "(" number (, number)* ")"
    **/

  val number: Regex = "[0-9]+".r

  def expr: Parser[List[Int]] = "(" ~> number ~ rep("," ~ number) <~ ")" ^^ {
    case n ~ m => n.toInt :: m.map(_._2.toInt)
  }
}
