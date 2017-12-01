package parsing

import scala.util.parsing.combinator.JavaTokenParsers

class Expr

case class Number(value: Int) extends Expr

case class Operator(op: String, left: Expr, right: Expr) extends Expr

/**
  * 6. Modify the parser in Section 20.5, “Generating Parse Trees,” on page 309 so that the
  * expression tree is correct. For example, 3-4-5 should yield an Operator("-", Operator("-", 3,
  * 4), 5).
  */
class MinusOpParser extends JavaTokenParsers {

  def term: Parser[Expr] = (factor ~ opt("*" ~> term)) ^^ {
    case a ~ None => a
    case a ~ Some(b) => Operator("*", a, b)
  }

  /**
    * 7. Suppose in Section 20.6, “Avoiding Left Recursion,” on page 310, we first parse an expr into a list of ~ with operations and values:
    * *
    * def expr: Parser[Int] = term ~ rep(("+" | "-") ~ term) ^^ {...}
    * *
    * To evaluate the result, we need to compute ((t0 ± t1) ± t2) ± . . .
    * Implement this computation as a fold (see Chapter 13).
    */
  def expr: Parser[Expr] = term ~ rep(("+" | "-") ~ term) ^^ {
    case left ~ right => right.foldLeft(left)(
      (value, exp) => exp match {
        case op ~ n => Operator(op, value, n)
      }
    )
  }

  def factor: Parser[Expr] = wholeNumber ^^ { n => Number(n.toInt) } | "(" ~> expr <~ ")"
}
