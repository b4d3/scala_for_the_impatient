package working_with_arrays

import scala.collection.mutable.ArrayBuffer

object Main {

  /**
    * 8. Suppose you are given an array buffer of integers and want to remove all but the first negative number.
    * Write it in Scala by collecting positions of the negative elements, dropping the first element, reversing the sequence, and calling a.remove(i) for each index.
    */
  def removeNegativesButFirst = {
    var a = ArrayBuffer(5, 1, -2, -5, 8, -9, 0, 10, -1, -5)
    println(a.mkString(" "))

    var indicesOfNegatives = (for (i <- a.indices if a(i) < 0) yield i).drop(1)
    val numberToTruncateArray = indicesOfNegatives.length

    var indicesOfPositives = if (indicesOfNegatives.nonEmpty) for (i <- indicesOfNegatives(0) until a.length if a(i) >= 0) yield i else Vector()
    val numberToTruncateIndices = indicesOfPositives.length

    for (i <- 0 until indicesOfPositives.length - indicesOfNegatives.length)
      indicesOfNegatives = indicesOfNegatives :+ indicesOfPositives(i)

    println(indicesOfNegatives)
    println(indicesOfPositives)

    indicesOfNegatives = (indicesOfNegatives ++ indicesOfPositives).sorted.take(numberToTruncateIndices)
    println(indicesOfNegatives)

    for (i <- indicesOfPositives.indices)
      a(indicesOfNegatives(i)) = a(indicesOfPositives(i))

    a = a.take(a.length - numberToTruncateArray)

    println(a.mkString(" "))
  }

}
