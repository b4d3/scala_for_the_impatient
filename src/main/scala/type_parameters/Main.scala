package type_parameters

object Main extends App {

  /**
    * 6. Write a generic method middle that returns the middle element from any Iterable[T].
    * For example, middle("World") is 'r'.
    */
  def middle[T](it: Iterable[T]): T = {
    assert(it.nonEmpty)

    if (it.size == 1)
      it.iterator.next()

    var position = 0
    val iterator = it.iterator

    while (iterator.hasNext && position < (it.size / 2)) {
      iterator.next()
      position += 1
    }

    iterator.next()
  }

  println(middle("World"))
}
