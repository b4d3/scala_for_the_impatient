package traits.DogsAndFrogs

trait Jumpify extends Animal {
  override def jump(fromWhere: String): Unit = super.jump(if (!isWalking) fromWhere + "back to water" else fromWhere)
}
