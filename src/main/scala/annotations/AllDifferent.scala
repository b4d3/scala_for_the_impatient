package annotations

class AllDifferent {

  /**
    * 8. Add the allDifferent method to an object, compile and look at the bytecode. What methods
    * did the @specialized annotation generate?
    */
  def allDifferent[@specialized T](x: T, y: T, z: T): Boolean = x != y && x != z && y != z
}
