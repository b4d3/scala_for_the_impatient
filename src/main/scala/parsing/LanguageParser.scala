package parsing

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

case class BooleanExpr(left: Expr, right: Expr, op: Function2[Double, Double, Boolean]) extends Expr

case class IfElseStatement(cond: BooleanExpr, ifExpr: Expr, elseExpr: Expr) extends Expr

case class WhileStatement(cond: BooleanExpr, whileExpr: Expr) extends Expr

case class FunctionDef(block: Expr) extends Expr

/**
  * 8. Add variables and assignment to the calculator program. Variables are created when they are first used. Uninitialized variables are zero. To print a value, assign it to the special variable out.
  * *
  * 9. Extend the preceding exercise into a parser for a programming language that has variable
  * assignments, Boolean expressions, and if/else and while statements.
  * *
  * 10. Add function definitions to the programming language of the preceding exercise.
  */
//TOO: Unfinished
class LanguageParser extends StandardTokenParsers {

  lexical.reserved += ("while", "if", "else")
  lexical.delimiters += (";", "=", "<", ">", "(", ")", "{", "}", "+", "-", "*", "/")


  private val valueMap = scala.collection.mutable.Map[String, Double]()
  private val funMap = scala.collection.mutable.Map[(String, List[String]), FunctionDef]()


  def term: Parser[Expr] = (factor ~ opt(("*" | "/" | "%") ~ term)) ^^ {
    case a ~ None => a
    case a ~ Some(op ~ b) => Operator(op, a, b)
  }

  def expr: Parser[Expr] = term ~ rep(("+" | "-") ~ term) ^^ {
    case left ~ right => right.foldLeft(left)(
      (value, exp) => exp match {
        case op ~ n => Operator(op, value, n)
      }
    )
  }

  def factor: Parser[Expr] = numericLit ^^ { n => Number(n.toInt) } | "(" ~> expr <~ ")"


  def variables: Parser[Unit] = (ident <~ "=") ~ expr ^^ {
    case "out" ~ e => println(e)
    case n ~ Number(e) => valueMap(n) = e
  }

  lazy val bools: Parser[BooleanExpr] = expr ~ "==|!=|<|>|<=|>=" ~ expr ^^ {
    case left ~ "==" ~ right => BooleanExpr(left, right, (a: Double, b: Double) => a == b)
    case left ~ "!=" ~ right => BooleanExpr(left, right, (a: Double, b: Double) => a != b)
    case left ~ "<=" ~ right => BooleanExpr(left, right, (a: Double, b: Double) => a <= b)
    case left ~ "<" ~ right => BooleanExpr(left, right, (a: Double, b: Double) => a < b)
    case left ~ ">" ~ right => BooleanExpr(left, right, (a: Double, b: Double) => a > b)
    case left ~ ">=" ~ right => BooleanExpr(left, right, (a: Double, b: Double) => a >= b)
  }

  def controlFlow: Parser[Expr] = (("if" | "while") <~ "(") ~ (bools <~ ")") ~ ("{" ~> expr <~ "}") ~ opt("else" ~> ("{" ~> expr <~ "}")) ^^ {
    case "if" ~ b ~ ifexpr ~ Some(elseexpr) => IfElseStatement(b, ifexpr, elseexpr)
    case "while" ~ b ~ whileExpr ~ None => WhileStatement(b, whileExpr)
  }

  def functions: Parser[Unit] = ("def" ~> ident <~ "(") ~ rep(ident <~ ":" ~ ident <~ ")") ~ ("{" ~> expr <~ "}") ^^ {
    case funName ~ args ~ block => funMap((funName, args)) = FunctionDef(block)
  }

}
