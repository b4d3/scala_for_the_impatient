package implicits

trait NumberLike[T] {
  def plus(x: T, y: T): T

  def divideBy(x: T, n: Int): T

}

object NumberLike {

  implicit object NumberLikeInt extends NumberLike[Int] {
    def plus(x: Int, y: Int): Int = x + y

    def divideBy(x: Int, n: Int): Int = x / n
  }

  implicit object NumberLikeDouble extends NumberLike[Double] {
    def plus(x: Double, y: Double): Double = x + y

    def divideBy(x: Double, n: Int): Double = x / n
  }

  implicit object NumberLikeBigDecimal extends NumberLike[BigDecimal] {
    def plus(x: BigDecimal, y: BigDecimal): BigDecimal = x + y

    def divideBy(x: BigDecimal, n: Int): BigDecimal = x / n
  }

  implicit object NumberLikeString extends NumberLike[String] {
    def plus(x: String, y: String): String = x + y

    def divideBy(x: String, n: Int): String = {
      val chars = x.toCharArray
      val ret = for (i <- chars.indices if i % n == 0) yield chars(i)
      ret.mkString
    }
  }

  /**
    * 10. Generalize the average method in Section 21.8, “Type Classes,” on page 331 to a Seq[T].
    */
  def average[T: NumberLike](list: Seq[T]): T = {
    val ev = implicitly[NumberLike[T]]
    ev.divideBy(list.reduceLeft(ev.plus), list.length)
  }
}