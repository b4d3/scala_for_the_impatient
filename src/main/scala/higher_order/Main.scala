package higher_order

object Main extends App {

  /**
    * 1. Write a function values(fun: (Int) => Int, low: Int, high: Int) that yields a collection of function inputs and outputs in a given range. For example, values(x => x * x, -5, 5) should produce a collection of pairs (-5, 25), (-4, 16), (-3, 9), . . . , (5, 25).
    */
  def values(f: Int => Int, low: Int, high: Int) = for (i <- low to high) yield (i, f(i))

  /**
    * 2. How do you get the largest element of an array with reduceLeft?
    */
  val largest = Array(4, 6, 1, 2, -2, -8, 122, 0, 1).reduceLeft((a, b) => if (a > b) a else b)

  /**
    * ~~3. Implement the factorial function using to and reduceLeft, without a loop or recursion.~~
    * 4. The previous implementation needed a special case when n < 1. Show how you can avoid this with foldLeft. (Look at the Scaladoc for foldLeft. It’s like reduceLeft, except that the first value in the chain of combined values is supplied in the call.)
    */
  def factorial(n: Int) = (1 to n).foldLeft(1)(_ * _)

  /**
    * ~ 5. Write a function largest(fun: (Int) => Int, inputs: Seq[Int]) that yields the largest
    * value of a function within a given sequence of inputs. For example, largest(x => 10 * x - x
    * * x, 1 to 10) should return 25. Don’t use a loop or recursion. ~
    * 6. Modify the previous function to return the input at which the output is largest. For example, largestAt(x => 10 * x - x * x, 1 to 10) should return 5. Don’t use a loop or recursion.
    */
  def largest(f: Int => Int, inputs: Seq[Int]) = (for (x <- inputs) yield (x, f(x))).reduceLeft((a, b) => if (a._2 > b._2) a else b)._1

  /**
    * 7. It’s easy to get a sequence of pairs, for example:
    *
    * val pairs = (1 to 10) zip (11 to 20)
    *
    * Now, suppose you want to do something with such a sequence—say, add up the values. But you can’t do
    *
    *   pairs.map(_ + _)
    *
    * The function _ + _ takes two Int parameters, not an (Int, Int) pair. Write a function adjustToPair that receives a function of type (Int, Int) => Int and returns the equivalent function that operates on a pair. For example, adjustToPair(_ * _)((6, 7)) is 42.
    *
    * Then use this function in conjunction with map to compute the sums of the elements in pairs.
    */
  def adjustToPair(op: (Int, Int) => Int) = (a: (Int, Int)) => op(a._1, a._2)

  /**
    * ~ 8. In Section 12.8, “Currying,” on page 164, you saw the corresponds method used with two
    * arrays of strings. Make a call to corresponds that checks whether the elements in an array
    * of strings have the lengths given in an array of integers. ~
    * 9. Implement corresponds without currying. Then try the call from the preceding exercise. What problem do you encounter?
    */
  def corresponds(A: Seq[String], B: Seq[String], p: (String, String) => Boolean): Boolean = {

    if (A.length != B.length) false

    for (i <- A.indices) if (!p(A(i), B(i))) return false

    true
  }

  /**
    * 10. Implement an unless control abstraction that works just like if, but with an inverted condition. Does the first parameter need to be a call-by-name parameter? Do you need currying?
    */
  def unless(condition: => Boolean)(block: => Unit) {
    if (!condition) {
      block
    }
  }

  unless(5 < 3) {
    println("H")
  }

}
