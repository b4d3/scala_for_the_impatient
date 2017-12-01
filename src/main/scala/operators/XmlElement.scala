package operators

import scala.language.dynamics

class XmlElement(val xml: String) extends Dynamic {

  def selectDynamic(name: String): XmlElement = {
    val r = s"""(?s).*?(<$name.*</$name>).*""".r
    xml match {
      case r(element) => new XmlElement(element)
      case _ => throw new NoSuchElementException
    }
  }

  def applyDynamicNamed(name: String)(args: (String, String)*): XmlElement = {
    val idTag = args(0)
    val r = s"""(?s).*?(<$name ${idTag._1}="${idTag._2}".*</$name>).*""".r
    xml match {
      case r(element) => new XmlElement(element)
      case _ => throw new NoSuchElementException
    }
  }

  override def toString: String = xml
}
