package implicits

object IntConvertor {

  /**
    * 2. Define an operator +% that adds a given percentage to a value. For example, 120 +% 10
    * should be 132. Use an implicit class.
    */
  implicit class Percentage(val n: Int) {

    def +%(percentage: Int): Double = n + percentage / 100.0 * n
  }

  /**
    * 3. Define a ! operator that computes the factorial of an integer. For example, 5.! is 120.
    * Use an implicit class.
    */
  implicit class Fact(val n: Int) {
    def ! : Int = {
      var product = 1
      for (i <- 1 to n) product = product * i
      product
    }
  }

}
