package traits

import java.awt.Point

/**
  * 2. Define a class OrderedPoint by mixing scala.math.Ordered[Point] into java.awt.Point. Use lexicographic ordering, i.e. (x, y) < (x’, y’) if x < x’ or x = x’ and y < y’.
  */
class OrderedPoint extends java.awt.Point with scala.math.Ordered[Point] {

  override def compare(that: Point): Int = {
    if (this.x < that.x)
      -1
    else if (this.x > that.x)
      1
    else if (this.y < that.y)
      -1
    else if (this.y > that.y)
      -1
    else
      0
  }

}

object OrderedPoint {
  def apply(x: Int, y: Int): OrderedPoint = {
    val point = new OrderedPoint()
    point.setLocation(x, y)
    point
  }
}

