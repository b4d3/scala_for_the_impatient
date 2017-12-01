package inheritance

import scala.collection.mutable.ArrayBuffer

/**
  * 4. Define an abstract class Item with methods price and description. A SimpleItem is an item whose price and description are specified in the constructor. Take advantage of the fact that a val can override a def. A Bundle is an item that contains other items. Its price is the sum of the prices in the bundle. Also provide a mechanism for adding items to the bundle and a suitable description method.
  */
class SimpleItem(val price: Double = 0, val description: String) extends Item

class Bundle {
  private val itemBundle = ArrayBuffer[Item]()

  def addItemToBundle(item: Item): Unit = {
    itemBundle += item
  }

  def description: String = itemBundle.map(_.description).mkString(", ")
}

