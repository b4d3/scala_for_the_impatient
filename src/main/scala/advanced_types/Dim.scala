package advanced_types

/**
  * 10. Use a self type to prevent <Illegal> part.
  */
abstract class Dim[T](val value: Double, val name: String) {

  this: T =>

  protected def create(v: Double): T

  def +(other: Dim[T]): T = create(value + other.value)

  override def toString = s"$value $name"
}

class Seconds(v: Double) extends Dim[Seconds](v, "s") {
  override def create(v: Double) = new Seconds(v)
}

// Illegal
//class Meters(v: Double) extends Dim[Seconds](v, "m") {
//  override def create(v: Double) = new Seconds(v)
//}
