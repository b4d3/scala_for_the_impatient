package operators

object PathComponents {

  //  def unapply(filepath: String): Option[(String, String)] = {
  //    val pos = filepath.lastIndexOf("/")
  //    if (pos == -1) None
  //    else Some((filepath.substring(0, pos + 1), filepath.substring(pos + 1)))
  //  }

  def unapplySeq(filepath: String): Option[Seq[String]] = {
    if (filepath.trim == "") None
    else if (filepath.trim == "/") Some(Array("<root>"))
    else if (filepath.startsWith("/")) Some(filepath.trim.split("/").drop(1))
    else Some(filepath.trim.split("/"))
  }
}
