package pattern_matching_case_classes

sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(op: String, values: BinaryTree*) extends BinaryTree