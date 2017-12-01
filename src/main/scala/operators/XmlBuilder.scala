package operators

import scala.language.dynamics

class XmlBuilder extends Dynamic {

  def applyDynamicNamed(name: String)(args: (String, String)*): XmlBuilderHelper = {

    var newPrefix = s"<$name "
    for ((attr, value) <- args) {
      if (value == "") newPrefix += s"$attr "
      else newPrefix += s"$attr=$value "
    }
    newPrefix += ">"

    new XmlBuilderHelper(newPrefix, s"</$name>")

  }

  def selectDynamic(name: String): XmlBuilderHelper = new XmlBuilderHelper(s"<$name>", s"</$name>")

  class XmlBuilderHelper(val xmlPrefix: String, val xmlSuffix: String) extends Dynamic {

    def applyDynamicNamed(name: String)(args: (String, String)*): XmlBuilderHelper = {

      var newPrefix = s"<$name "
      for ((attr, value) <- args) {
        if (value == "") newPrefix += s"$attr "
        else newPrefix += s"$attr=$value "
      }
      newPrefix += ">"

      new XmlBuilderHelper(xmlPrefix + newPrefix, s"</$name>" + xmlSuffix)
    }

    def selectDynamic(name: String): XmlBuilderHelper = new XmlBuilderHelper(xmlPrefix + s"<$name>", s"</$name>" + xmlSuffix)

    override def toString: String = xmlPrefix + xmlSuffix
  }

}