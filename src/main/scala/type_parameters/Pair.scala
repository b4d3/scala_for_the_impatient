package type_parameters

/**
  * 1. Define an immutable class Pair[T, S] with a method swap that returns a new pair with the
  * components swapped.
  */
class Pair[T, S](val t: T, val s: S) {

  def swap: Pair[S, T] = new Pair(s, t)

  def swap(pair: Pair[T, S]): Pair[S, T] = pair.swap
}
