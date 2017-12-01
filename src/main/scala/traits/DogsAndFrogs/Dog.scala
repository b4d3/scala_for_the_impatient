package traits.DogsAndFrogs

class Dog extends Jumpify {
  override val size: Int = 10

  override def express(): Unit = println("Bark woof!")
}
