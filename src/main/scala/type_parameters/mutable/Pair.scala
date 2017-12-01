package type_parameters.mutable

/**
  * 3. Given a class Pair[T, S], write a generic method swap that takes a pair as its argument
  * and returns a new pair with the components swapped.
  */
class Pair[T, S](var first: T, var second: S) {

  /**
    * 10. Given a mutable Pair[S, T] class, use a type constraint to define a swap method that
    * can be called if the type parameters are the same.
    */
  def swap[T, S]()(implicit ev: S =:= T) = {
    //    val tmp = first.asInstanceOf[S]
    //    first = second.asInstanceOf[T]
    //    second = tmp
  }
}
