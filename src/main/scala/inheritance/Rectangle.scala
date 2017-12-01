package inheritance

class Rectangle(a: Double, b: Double) extends Shape {
  override def centerPoint: Point = new Point(a / 2, b / 2)
}
