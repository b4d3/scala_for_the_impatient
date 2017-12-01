package objects

/**
  * 8. Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values (for example, 0xff0000 for Red).
  */
object Rgb extends Enumeration {
  val Red: Rgb.Value = Value(0xff0000)
  val Black: Rgb.Value = Value(0x000000)
  val Magenta: Rgb.Value = Value(0xff00ff)
  val Green: Rgb.Value = Value(0x00ff00)
  val Yellow: Rgb.Value = Value(0xffff00)
  val White: Rgb.Value = Value(0xffffff)
  val Cyan: Rgb.Value = Value(0x00ffff)
  val Blue: Rgb.Value = Value(0x0000ff)
}


