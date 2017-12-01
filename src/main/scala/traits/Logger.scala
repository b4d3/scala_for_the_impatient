package traits

trait Logger {
  def log(msg: String): Unit = println(msg)
}
