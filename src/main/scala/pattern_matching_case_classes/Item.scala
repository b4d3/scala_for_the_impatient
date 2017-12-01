package pattern_matching_case_classes

abstract class Item

case class Article(description: String, price: Double) extends Item

case class Bundle(description: String, discount: Double, items: Item*) extends Item

case class Multiple(numberOf: Int, items: Item*) extends Item

