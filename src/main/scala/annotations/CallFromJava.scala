package annotations

import java.io.IOException

import scala.annotation.varargs
import scala.io.Source

class CallFromJava {

  /**
    * 4. Write a Scala method sum with variable integer arguments that returns the sum of its
    * arguments. Call it from Java.
    */
  @varargs
  def sum(ints: Int*): Int = ints.sum

  /**
    * 5. Write a Scala method that returns a string containing all lines of a file. Call it from
    * Java.
    */
  @throws(classOf[IOException])
  def allLines(filepath: String): String = Source.fromFile(filepath).mkString
}
