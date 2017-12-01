package parsing

import scala.util.parsing.combinator.RegexParsers

class ExprParser extends RegexParsers {

  /**
    * expr ::= term ( ( "+" | "-" ) expr )?
    * term ::= factor ( "*" factor )*
    * factor ::= number | number("^"number)* | "(" expr ")"
    **/

  val number = "[0-9.]+".r

  def expr: Parser[Double] = term ~ rep(("+" | "-") ~ term) ^^ {
    case left ~ right => right.foldLeft(left)(
      (value, exp) => exp match {
        case "+" ~ n => value + n
        case "-" ~ n => value - n
      }
    )
  }

  /**
    * 1. Add / and % operations to the arithmetic expression evaluator.
    */
  def term: Parser[Double] = factor ~ opt(("*" | "/" | "%") ~ term) ^^ {
    case f ~ None => f
    case f ~ Some("*" ~ e) => f * e
    case f ~ Some("/" ~ e) => f / e
    case f ~ Some("%" ~ e) => f % e
  }

  /**
    * 2. Add a ^ operator to the arithmetic expression evaluator. As in mathematics, ^ should
    * have a higher precedence than multiplication, and it should be right-associative. That is,
    * 4^2^3 should be 4^(2^3), or 65536.
    */
  def factor: Parser[Double] = number ^^ {
    _.toDouble
  } | number ~ opt("^" ~ factor) ^^ {
    case n ~ None => n.toDouble
    case n ~ Some("^" ~ m) => math.pow(n.toDouble, m)
  } | "(" ~> expr <~ ")"

}