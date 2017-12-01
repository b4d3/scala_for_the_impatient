package collections

import scala.collection.mutable
import scala.collection.immutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main extends App {

  /**
    * 1. Write a function that, given a string, produces a map of the indexes of all characters.
    * For example, indexes("Mississippi") should return a map associating 'M' with the set {0}, 'i' with the set {1, 4, 7, 10}, and so on. Use a mutable map of characters to mutable sets. How can you ensure that the set is sorted?
    */
  def indexesMut(word: String): mutable.Map[Char, mutable.SortedSet[Int]] = {
    val charMap = mutable.Map[Char, mutable.SortedSet[Int]]()
    for (ci <- word.zipWithIndex) charMap += ci._1 -> (charMap.getOrElse(ci._1, mutable.SortedSet[Int]()) += ci._2)
    charMap
  }

  /**
    * 2. Repeat the preceding exercise, using an immutable map of characters to lists.
    */
  def indexes(word: String): Map[Char, immutable.SortedSet[Int]] = {
    def iter(indexedLetters: Seq[(Char, Int)], currMap: Map[Char, immutable.SortedSet[Int]]): Map[Char, immutable.SortedSet[Int]] = {
      if (indexedLetters.isEmpty) currMap
      else iter(indexedLetters.tail,
        currMap + (indexedLetters(0)._1 -> (currMap.getOrElse(indexedLetters(0)._1, immutable.SortedSet[Int]()) + indexedLetters(0)._2)))
    }

    iter(word.zipWithIndex, Map())
  }

  /**
    * 3. Write a function that removes every second element from a ListBuffer. Try it two ways.
    * Call remove(i) for all even i starting at the end of the list. Copy every second element to
    * a new list. Compare the performance.
    */
  def removeOdd[T](list: ListBuffer[T]): ListBuffer[T] = {
    for (i <- list.indices.reverse) if (i % 2 != 0) list.remove(i)
    list
  }

  def removeOddByCopying[T](list: ListBuffer[T]): ListBuffer[T] = {
    var newList = mutable.ListBuffer[T]()
    for (i <- list.indices) if (i % 2 == 0) newList += list(i)
    newList
  }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }


  /**
    * 4. Write a function that receives a collection of strings and a map from strings to
    * integers. Return a collection of integers that are values of the map corresponding to one of
    * the strings in the collection. For example, given Array("Tom", "Fred", "Harry") and Map
    * ("Tom" -> 3, "Dick" -> 4, "Harry" -> 5), return Array(3, 5).
    * Hint: Use flatMap to combine the Option values returned by get.
    */
  def collectValues(seq: Seq[String], mapy: Map[String, Int]): Seq[Int] = seq.flatMap(mapy.get)

  println(collectValues(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)))

  /**
    * 5. Implement a function that works just like mkString, using reduceLeft.
    */
  def myMkString[T](seq: Seq[T], between: String): String = seq.map(_.toString).reduceLeft(_ + between + _)

  /**
    * 6. Given a list of integers lst, what is (lst :\ List[Int]())(_ :: _)? (List[Int]() /: lst)
    * (_ :+ _)? How can you modify one of them to reverse the list?
    */
  val lst = List(1, 2, 3)
  println((lst :\ List[Int]()) (_ :: _))
  println((List[Int]() /: lst) (_ :+ _))
  println((List(1, 2, 3) :\ List[Int]()) ((a: Int, b: List[Int]) => b :+ a))
  println((List[Int]() /: List(1, 2, 3)) ((a: List[Int], b: Int) => b :: a))

  /**
    * 7. In Section 13.10, “Zipping,” on page 187, the expression (prices zip quantities) map { p
    * => p._1 * p._2 } is a bit inelegant. We can’t do (prices zip quantities) map { _ * _ }
    * because _ * _ is a function with two arguments, and we need a function with one argument
    * that is a tuple. The tupled method of the Function object changes a function with two
    * arguments to one that takes a tuple. Apply tupled to the multiplication function so you can
    * map it over the list of pairs.
    **/
  val prices = List(0.2, 0.3, 0.5)
  val quantities = List(2, 5, 6)
  println((prices zip quantities) map {
    Function.tupled(_ * _)
  })

  /**
    * 8. Write a function that turns an array of Double values into a two-dimensional array. Pass
    * the number of columns as a parameter. For example, with Array(1, 2, 3, 4, 5, 6) and three
    * columns, return Array(Array(1, 2, 3), Array(4, 5, 6)). Use the grouped method.
    */
  def toMatrix(arr: Array[Double], numberOfColumns: Int) = arr.grouped(numberOfColumns).toArray

  val matrix = toMatrix(Array(1, 2, 3, 4, 5, 6), 3)
  for (a <- matrix) println(a.mkString(" "))

  /**
    * 10. The method java.util.TimeZone.getAvailableIDs yields time zones such as Africa/Cairo
    * and Asia/Chungking. Which continent has the most time zones? Hint: groupBy.
    */
  println(java.util.TimeZone.getAvailableIDs().groupBy(_.takeWhile(_ != '/')).maxBy(_._2.length))

  /**
    *
    *11. Harry Hacker reads a file into a string and wants to use a parallel collection to update the
    * letter frequencies concurrently on portions of the string. He uses the following code:
    * *
    * val frequencies = new scala.collection.mutable.HashMap[Char, Int]
    * *
    * for (c <- str.par) frequencies(c) = frequencies.getOrElse(c, 0) + 1
    * *
    * Why is this a terrible idea? How can he really parallelize the computation? (Hint: Use
    *aggregate.)
    */
  println("some_saaaassstring_file"
    .par.aggregate(Map[Char, Int]())
  ((m, c) => m + (c -> (m.getOrElse(c, 0) + 1)),
    (map1, map2) => map1 ++ map2.map { case (k, v) => k -> (v + map1.getOrElse(k, 0)) }))

}
