package maps_and_tuples

object Main {

  /**
    * 8. Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and the largest values in the array.
    */
  def minmax(values: Array[Int]): (Int, Int) = (values.min, values.max)

  /**
    * 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing the counts of values less than v, equal to v, and greater than v.
    */
  def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = (values.count(_ < v), values.count(_ == v), values.count(_ > v))

}
