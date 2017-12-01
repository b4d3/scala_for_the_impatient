package advanced_types

object Title

object Author

/**
  * 3. Complete the fluent interface in Section 19.1, “Singleton Types,” on page 280 so that one can call
  *
  * book set Title to "Scala for the Impatient" set Author to "Cay Horstmann"
  */
class Document {

  private var author: String = ""
  private var title: String = ""
  private var useNextArgAs: Any = _

  def setTitle(title: String): this.type = {
    this.title = title
    this
  }

  def setAuthor(author: String): this.type = {
    this.author = author
    this
  }

  def set(obj: Title.type): this.type = {
    useNextArgAs = obj;
    this
  }

  def set(obj: Author.type): this.type = {
    useNextArgAs = obj;
    this
  }

  def to(arg: String): this.type = if (useNextArgAs == Title) setTitle(arg) else if (useNextArgAs == Author) setAuthor(arg) else this

  override def toString: String = author + " - " + title
}