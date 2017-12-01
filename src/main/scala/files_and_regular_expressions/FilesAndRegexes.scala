package files_and_regular_expressions

import java.nio.file.{Files, Paths}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by bade on 11.05.17..
  */
object FilesAndRegexes extends App {


  /**
    * 8. Write a Scala program that prints the src attributes of all img tags of a web page. Use regular expressions and groups.
    */

  val urlString = Source.fromFile("""/media/bade/Data/Programiranje/ch9.html""").mkString

  val srcImgRegex = """<img src="(.*)?"""".r

  println(srcImgRegex.findAllMatchIn(urlString).count(_ => true))

  for (m <- srcImgRegex.findAllMatchIn(urlString))
    println(m.group(1))


  /**
    * 9. Write a Scala program that counts how many files with .class extension are in a given directory and its subdirectories.
    */
  Files.walk(Paths.get(".")).filter(_.getFileName.toString.endsWith(".class")).forEach(println(_))


  /**
    * 10. Expand the example in Section 9.8, “Serialization,” on page 113. Construct a few Person objects, make some of them friends of others, and save an Array[Person] to a file. Read the array back in and verify that the friend relations are intact.
    *
    * @param name
    */
  class Person(val name: String) extends Serializable {
    val friends = new ArrayBuffer[Person]
  }

  val me = new Person("Bad")
  val he = new Person("Ant")
  val someone = new Person("Net")
  me.friends += he
  me.friends += someone
  he.friends += me

  val friends = ArrayBuffer(me, he, someone)

  import java.io._

  val out = new ObjectOutputStream(new FileOutputStream("/tmp/test.obj"))
  out.writeObject(friends)
  out.close()
  val in = new ObjectInputStream(new FileInputStream("/tmp/test.obj"))
  val savedFriends = in.readObject().asInstanceOf[ArrayBuffer[Person]]

  println(savedFriends(0).name)

}
