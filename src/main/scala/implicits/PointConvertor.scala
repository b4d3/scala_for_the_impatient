package implicits

import java.awt.Point

object PointConvertor {

  implicit class RichPoint(point: java.awt.Point) extends Ordered[java.awt.Point] {
    override def compare(that: Point): Int = {
      if (point.x > that.x) 1
      else if (point.x < that.x) -1
      else if (point.y > that.y) 1
      else if (point.y < that.y) -1
      else 0
    }
  }

  implicit class RichPointOrigin(point: java.awt.Point) extends Ordered[java.awt.Point] {
    override def compare(that: Point): Int = {
      val thisDistance = math.sqrt(point.x * point.x + point.y * point.y)
      val thatDistance = math.sqrt(that.x * that.x + that.y * that.y)

      if (thisDistance < thatDistance) -1
      else if (thisDistance > thatDistance) 1
      else 0
    }
  }

}
