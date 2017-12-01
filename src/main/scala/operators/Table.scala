package operators

class Table() {

  val BEGIN = "<table><tr>"
  val END = "</tr></table>"

  private var html = BEGIN

  def |(tag: String): Table = {
    html += s"<td>$tag</td>"
    this
  }

  def ||(tag: String): Table = {
    html += s"</tr><tr><td>$tag</td>"
    this
  }

  override def toString: String = html + END

}

object Table {
  def apply(): Table = new Table()
}
