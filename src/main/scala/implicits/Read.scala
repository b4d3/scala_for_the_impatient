package implicits

import scala.io.StdIn

object aString

object anInt

object aDouble

object and

/**
  *
  * *
  *4. Some people are fond of “fluent APIs” that read vaguely like English sentences. Create such an
  * API for reading integers, floating-point numbers, and strings from the console.
  * For example: Read in aString askingFor "Your name" and anInt askingFor "Your age" and aDouble
  * askingFor "Your weight".
  */
object Read {

  var name: String = _
  var age: Int = _
  var weight: Double = _

  var input: StdIn.type = _
  var useNextArgAs: Any = _

  override def toString: String = s"Name: $name, age: $age, weight: $weight"

  implicit class FluentReader(read: Read.type) {

    def in(obj: aString.type): this.type = {
      useNextArgAs = obj
      in
    }

    def in(obj: anInt.type): this.type = {
      useNextArgAs = obj
      in
    }

    def in(obj: aDouble.type): this.type = {
      useNextArgAs = obj
      in
    }

    def and(obj: aString.type): this.type = {
      useNextArgAs = obj
      this
    }

    def and(obj: anInt.type): this.type = {
      useNextArgAs = obj
      this
    }

    def and(obj: aDouble.type): this.type = {
      useNextArgAs = obj
      this
    }

    private def in: this.type = {
      input = scala.io.StdIn
      this
    }

    def askingFor(question: String): this.type = {
      println(question)

      if (useNextArgAs == anInt)
        age = input.readInt()
      else if (useNextArgAs == aDouble)
        weight = input.readDouble()
      else if (useNextArgAs == aString)
        name = input.readLine()

      this
    }
  }

}