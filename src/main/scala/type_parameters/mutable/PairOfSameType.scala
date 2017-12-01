package type_parameters.mutable


/**
  * 2. Define a mutable class Pair[T] with a method swap that swaps the components of the pair.
  */
class PairOfSameType[T](var t: T, var s: T) {

  def swap(): Unit = {
    val tmp = t
    t = s
    s = tmp
  }
}
