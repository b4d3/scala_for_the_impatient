package type_parameters

/**
  * 9. It may seem strange to restrict method parameters in an immutable class Pair[+T].
  * However,
  * suppose you could define
  * *
  * def replaceFirst(newFirst: T)
  * *
  * in a Pair[+T]. The problem is that this method can be overridden in an unsound way. Construct
  * an example of the problem. Define a subclass NastyDoublePair of Pair[Double] that overrides
  * replaceFirst so that it makes a pair with the square root of newFirst.
  * Then construct the call replaceFirst("Hello") on a Pair[Any] that is actually a NastyDoublePair.
  */
class PairOfSameType[+T](val t: T, val s: T) {

  //  This won't compile
  //    def replaceFirst(newFirst: T) = new PairOfSameType(newFirst, s)
}

//
//class NastyDouble(t: Double, s: Double) extends PairOfSameType[Double](t, s) {
//
//  override def replaceFirst(newFirst: Double): PairOfSameType[Double] = new NastyDouble(scala.math.sqrt(newFirst), s)
//}
//
//val stringy: Pair[Any] = NastyDouble(2, 3)
//stringy.replaceFirst("world")
