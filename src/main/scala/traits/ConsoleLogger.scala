package traits

trait ConsoleLogger extends Logger {
  val a = 10
  override def log(msg: String) { println(msg) }
}
