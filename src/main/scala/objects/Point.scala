package objects

/**
  * 4. Define a Point class with a companion object so that you can construct Point instances as
  * Point(3, 4), without using new.
  */
class Point(x: Int, y: Int)

object Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)
}

