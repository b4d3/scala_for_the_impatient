package operators

import scala.language.dynamics


class DynamicProps(val props: java.util.Properties) extends Dynamic {

  //  def updateDynamic(name: String)(value: String) {
  //    new DynamicHelper(name, value)
  //  }

  def selectDynamic(name: String): DynamicHelper = {
    new DynamicHelper(name, "")
  }

  class DynamicHelper(val path: String, val property: String) extends Dynamic {

    def selectDynamic(name: String): DynamicHelper = {
      new DynamicHelper(path + s".$name", props.getProperty(path + s".$name"))
    }

    def updateDynamic(name: String)(value: String) = props.setProperty(path + s".$name", value)

    override def toString: String = property
  }

}