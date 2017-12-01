package traits

/**
  * 4. Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher. The key should be 3 by default, but it should be overridable by the user. Provide usage examples with the default key and a key of –3.
  */
trait CryptoLogger extends Logger {

  var key = 3

  def caesarCrypt(msg: String): String = {

    def cryptify(c: Char): Char = {
      key = if (key < 0) 26 + key else key

      if (c >= 65 && c <= 90)
        (((c - 64 + key) % 26) + 64).asInstanceOf[Char]
      else if (c >= 97 && c <= 122)
        (((c - 96 + key) % 26) + 96).asInstanceOf[Char]
      else
        c
    }

    val cipher = for (c <- msg) yield cryptify(c)
    cipher.mkString
  }

  override def log(msg: String): Unit = super.log(caesarCrypt(msg))
}