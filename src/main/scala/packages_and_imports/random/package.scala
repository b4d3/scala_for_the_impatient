package packages_and_imports

/**
  * 3. Write a package random with functions nextInt(): Int, nextDouble(): Double, and setSeed(seed: Int): Unit. To generate random numbers, use the linear congruential generator

        next = (previous × a + b) mod 2n,

    where a = 1664525, b = 1013904223, n = 32, and the initial value of previous is seed.
  */
package object random {

  def a = 1664525

  def b = 1013904223

  def n = 32

  var seed = 1
  var previous: Int = seed

  def setSeed(s: Int) {
    seed = s
  }

  def nextInt(): Int = {
    val next = (previous * a + b) % math.pow(2, n)
    previous = next.toInt
    next.toInt
  }

  def nextDouble(): Double = {
    val next = (previous.toDouble * a + b) % math.pow(2, n)
    previous = next.toInt
    next
  }

}
