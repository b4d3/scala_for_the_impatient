package classes

/**
  * 6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,” on page 55, provide a primary constructor that turns negative ages to 0.
  */
class PersonWithAge(_age: Int) {
  var age: Int = if (_age < 0) 0 else _age
}