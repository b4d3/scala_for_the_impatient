package xml_processing

import scala.xml.XML

object Main extends App {

  /**
    * 4. Read an XHTML file and print all img elements that don’t have an alt attribute.
    */
  val root = XML.loadFile("src/main/scala/sample.xhtml")

  root match {
    case n @ <img/> if n.attributes.get("alt").isEmpty => println(n)
  }
}