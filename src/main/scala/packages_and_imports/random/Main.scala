package packages_and_imports.random

object Main {

  /**
    * 6. Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both classes.
    */
  {

    import java.util.{HashMap => JavaHashMap}
    import scala.collection.JavaConversions._

    import scala.collection.mutable.{Map => ScalaHashMap}
    val javaMap = new JavaHashMap[String, String]
    val scalaHashMap = ScalaHashMap[String, String]()

    javaMap.put("asd", "##asd")
    javaMap.put("bla", "##bla")

    for ((k, v) <- javaMap)
      scalaHashMap(k) = v

    println(scalaHashMap)
  }

}
