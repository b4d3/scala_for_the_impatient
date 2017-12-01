package traits

import java.io.PrintWriter

trait FileLogger extends Logger{
  val out = new PrintWriter("app.log") // Part of the trait's constructor
  out.println(s"# ${java.time.Instant.now()}") // Also part of the constructor

  override def log(msg: String) { out.println(msg); out.flush() }
}
