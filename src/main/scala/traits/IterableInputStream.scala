package traits

import java.io.InputStream

/**
  * 11. Implement a class IterableInputStream that extends java.io.InputStream with the trait Iterable[Byte].
  */
abstract class IterableInputStream extends InputStream with Iterable[Byte] {
  var nextElement = 0

  override def iterator: Iterator[Byte] = new Iterator[Byte] {
    override def hasNext: Boolean = {
      nextElement = read()
      nextElement == -1
    }

    override def next(): Byte = nextElement.toByte
  }
}
