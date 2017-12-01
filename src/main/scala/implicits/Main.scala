import implicits.{Read, aDouble, aString, anInt}
import implicits.IntConvertor._
import operators.Fraction
import implicits.FractionConvertor.RichFraction
import implicits.PointConvertor.RichPointOrigin
import implicits.NumberLike._

import scala.collection.mutable.ArrayBuffer

object Main extends App {


  println(120 +% 10)

  println(5 !)

  Read in aString askingFor "Your name" and anInt askingFor "Your age" and aDouble askingFor "Your weight"
  println(Read)


  /**
    * 5. Provide the machinery that is needed to compute
    * smaller(Fraction(1, 7), Fraction(2, 9))
    * with the Fraction class of Chapter 11. Supply an implicit class RichFraction that extends
    * Ordered[Fraction].
    */
  def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if (a < b) a else b

  println(smaller(Fraction(1, 7), Fraction(2, 9)))

  /**
    * 6. Compare objects of the class java.awt.Point by lexicographic comparison
    */

  val p1 = new java.awt.Point(2, 3)
  val p2 = new java.awt.Point(3, 1)

  println(smaller(p1, p2))

  /**
    * 7. Continue the previous exercise, comparing two points according to their distance to the
    * origin. How can you switch between the two orderings?
    */

  println(smaller(p1, p2))

  println(average(ArrayBuffer(1, 2, 30)))


  /**
    * 11. Make String a member of the NumberLike type class in Section 21.8, “Type Classes,” on page
    * 331. The divBy method should retain every nth letter, so that average("Hello", "World") becomes "Hlool"
    */
  println(average(ArrayBuffer("Hello", "World")))

}
