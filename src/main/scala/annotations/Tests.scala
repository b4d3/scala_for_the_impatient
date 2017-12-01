package annotations

/**
  * 2. Make an example class that shows every possible position of an annotation. Use @deprecated
  * as your sample annotation.
  */
@deprecated class Tests[@specialized T](@deprecated val a: T = 4) {

  @deprecated(message = "Use nothing instead")
  def getVal: T = a

  @deprecated
  val b = 1

}
