package inheritance

class Circle(r: Double) extends Shape {
  override def centerPoint: Point = new Point(r / 2, r / 2)
}
