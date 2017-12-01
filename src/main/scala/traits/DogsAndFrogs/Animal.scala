package traits.DogsAndFrogs

/**
  * 8. There are dozens of Scala trait tutorials with silly examples of barking dogs or philosophizing frogs. Reading through contrived hierarchies can be tedious and not very helpful, but designing your own is very illuminating. Make your own silly trait hierarchy example that demonstrates layered traits, concrete and abstract methods, and concrete and abstract fields.
  */
trait Animal {
  val size: Int
  var isWalking = true

  def express()

  def jump(fromWhere: String): Unit = println(s"Jumped from $fromWhere")
}
