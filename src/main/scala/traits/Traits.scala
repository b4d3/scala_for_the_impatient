package traits

import java.io.InputStream

/**
  * Created by bade on 30.05.17..
  */
object Traits extends App {

  val bw = new InputStream with Buffering
  bw.log("now")

  val cipherLogger = new CryptoLogger {
    key = -3
  }

  cipherLogger.log("Alo")

  val p1 = OrderedPoint(5, 2)
  val p2 = OrderedPoint(5, 3)

  println(p1 == p2)

  val sa = new SavingsAccount
  println(sa.n)
  println(sa.a)
}
