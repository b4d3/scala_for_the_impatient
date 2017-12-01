package traits

import java.io.{BufferedInputStream, InputStream}

import Traits.Logger

/**
  * 9. In the java.io library, you add buffering to an input stream with a BufferedInputStream decorator. Reimplement buffering as a trait. For simplicity, override the read method.
  */
trait Buffering extends Logger {

  this: InputStream =>

  val b = new BufferedInputStream(this)

  def read(): Int = {
    b.read()
  }

  override def log(msg: String): Unit = super.log(msg)
}
