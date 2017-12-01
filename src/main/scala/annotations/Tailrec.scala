package annotations

import scala.annotation.tailrec

object Tailrec extends App {

  /**
    * 7. Give an example to show that the tail recursion optimization is not valid when a method
    * can be overridden.
    */
  @tailrec
  private def factorial(n: Int, f: Int): Int = {

    /**
      * 10. Add assert(n >= 0) to a factorial method. Compile with assertions enabled and verify
      * that factorial(-1) throws an exception. Compile without assertions. What happens? Use
      * javap to check what happened to the assertion call.
      */
    assert(n >= 0)
    if (n == 1) f else factorial(n - 1, n * f)
  }

  println(factorial(5, 1))
  println(factorial(-1, 1))

}
