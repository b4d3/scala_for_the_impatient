package advanced_types

class Book extends Document {

  private var chapter = ""

  def addChapter(chapter: String): this.type = {
    this.chapter = chapter
    this
  }
}
