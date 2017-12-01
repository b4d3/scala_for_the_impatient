package advanced_types

object Main extends App {

  val bugsy = new Bug
  bugsy move 4 and Show and Then move 6 and Show turn Around move 5 and Show

  val book = new Book
  book set Title to "Scala for the Impatient" set Author to "Cay Horstmann"
  println(book)

  val chatter = new Network
  val myFace = new Network

  val fred = chatter.join("Fred") // Has type chatter.Member
  val barney = myFace.join("Barney") // Has type myFace.Member
  val wilma = chatter.join("Wilma")

  assert(fred == wilma)
  assert(fred != barney)

  /**
    * 6. The Either type in the Scala library can be used for algorithms that return either a
    * result or some failure information. Write a function that takes two parameters: a sorted
    * array of integers and an integer value. Return either the index of the value in the array
    * or the index of the element that is closest to the value. Use an infix type as the return
    * type.
    */
  def getIndex(arr: Array[Int], number: Int): Either[Int, Int] = {
    val index = arr.indexOf(number)

    if (index == -1) {
      val differences = arr.map(_ - number).map(math.abs)
      Right(differences.indexOf(differences.min))
    } else
      Left(index)
  }

  assert(getIndex(Array(1, 2, 3, 5, 10), 5) == Left(3))
  assert(getIndex(Array(1, 12, 3, 5, 100), 9) == Right(1))

  /**
    * 7. Implement a method that receives an object of any class that has a method
    *
    * def close(): Unit
    *
    * together with a function that processes that object. Call the function and invoke the close
    * method upon completion, or when any exception occurs.
    */
  def processClosable(closable: {def close(): Unit}): Unit = {
    println("Calling close on closable")
    closable.close()
  }

  processClosable(new Closable)


  /**
    * 8. Write a function printValues with three parameters f, from, to that prints all values of f with inputs from the given range. Here, f should be any object with an apply method that consumes and yields an Int. For example,
    * *
    * printValues((x: Int) => x * x, 3, 6) // Prints 9 16 25 36
    * *
    * printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6) // Prints 3 5 8 13
    */
  def printValues(f: {def apply(x: Int): Int}, from: Int, to: Int): Unit = {
    for (i <- from to to) print(f.apply(i) + " ")
  }

  //  printValues((x: Int) => x * x, 3, 6)  // Bug in 2.12 compiler
  printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)


  /**
    * 10. Self types can usually be replaced with traits that extend classes, but there can be
    * situations where using self types changes the initialization and override orders. Construct
    * such an example.
    */
  trait LoggedSelfException {
    this: Exception =>

    def log(): Unit = print(getMessage)
  }

  trait LoggedException extends Exception {
    def log(): Unit = print(getMessage)
  }

  trait MySelfLoggedException extends Exception with LoggedSelfException

  trait MyLoggedException extends Exception with LoggedException

}
