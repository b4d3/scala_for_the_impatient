package inheritance

/**
  * 11. Define a value class Point that packs integer x and y coordinates into a Long (which you should make private).
  */
class PointVal private(val point: Long) extends AnyVal

object PointVal {
  def apply(p: Long): Point = new Point(p)
}

