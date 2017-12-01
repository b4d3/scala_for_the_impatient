package classes

/**
  * 7. Write a class Person with a primary constructor that accepts a string containing a first name, a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName and lastName. Should the primary constructor parameter be a var, a val, or a plain parameter? Why?
  */
class Person(val fullName: String) {
  val firstName: String = fullName.split(" ")(0)
  val lastName: String = fullName.split(" ")(1)
}