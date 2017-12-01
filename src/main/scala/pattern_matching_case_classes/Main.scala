package pattern_matching_case_classes

object Main extends App {

  /**
    * 2. Using pattern matching, write a function swap that receives a pair of integers and
    * returns the pair with the components swapped.
    */
  def swap(ints: (Int, Int)): (Int, Int) = {
    val (a, b) = ints
    (b, a)
  }

  /**
    * 3. Using pattern matching, write a function swap that swaps the first two elements of an
    * array provided its length is at least two.
    */
  def swapFirstTwo(arr: Array[Int]) = arr match {
    case Array(a, b, rest@_*) => Array(b, a) ++ rest
    case _ => arr
  }

  /**
    * 4. Add a case class Multiple that is a subclass of the Item class. For example, Multiple
    * (10, Article("Blackwell Toaster", 29.95)) describes ten toasters. Of course, you should be
    * able to handle any items, such as bundles or multiples, in the second argument. Extend the
    * price function to handle this new case.
    */
  def price(it: Item): Double = it match {
    case Article(_, p) => p
    case Bundle(_, disc, its@_*) => its.map(price).sum - disc
    case Multiple(n, its@_*) => its.map(price).sum * n
  }

  val bundle = Multiple(10, Bundle("Father's day special", 20.0,
    Article("Scala for the Impatient", 39.95),
    Bundle("Anchor Distillery Sampler", 10.0,
      Article("Old Potrero Straight Rye Whiskey", 79.95),
      Article("Junípero Gin", 32.95))))

  println(price(bundle))


  /**
    * 5. One can use lists to model trees that store values only in the leaves. For example, the
    * list ((3 8) 2 (5)). However, some of the list elements are numbers and others are lists. In
    * Scala, you cannot have heterogeneous lists, so you have to use a List[Any]. Write a leafSum
    * function to compute the sum of all elements in the leaves, using pattern matching to
    * differentiate between numbers and lists.
    */
  def leafSum(lst: List[Any]): Int = {
    if (lst.isEmpty) 0
    else lst.head match {
      case a: Int => a + leafSum(lst.tail)
      case l: List[Any] => leafSum(l) + leafSum(lst.tail)
    }
  }

  /**
    * 6. A better way of modeling such trees is with case classes. Let’s start with binary trees.
    *
    * sealed abstract class BinaryTree
    * *
    * case class Leaf(value: Int) extends BinaryTree
    * *
    * case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
    * *
    * Write a function to compute the sum of all elements in the leaves.
    */
  def treeSum(tree: BinaryTree): Int = tree match {
    case Leaf(value) => value
    case Node(op, values@_*) => op match {
      case "+" => values.map(treeSum).sum
      case "-" => -values.map(treeSum).sum
      case "*" => values.map(treeSum).product
      case "/" => values.map(treeSum).reduceLeft(_ / _)
    }
  }

  /**
    * 7. Extend the tree in the preceding exercise so that each node can have an arbitrary number of children, and reimplement the leafSum function. The tree in Exercise 5 should be expressible as
    *
    * Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
    */
  def listSum(lst: List[Option[Int]]) = lst.map(_.getOrElse(0)).sum

  //  val lst = List(5, List(1, 2), 3, List(4,5))
  //  println(leafSum(lst))

  //  val tree = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
  //  println(treeSum(tree))

  //  val opTree = Node("+", Node("*", Leaf(3), Leaf(8)), Leaf(2), Node("-", Leaf(5)))
  //  println(treeSum(opTree))

  //  println(listSum(List(Some(5), None, None, Some(3), Some(10))))

  /**
    * 10. Write a function that composes two functions of type Double => Option[Double], yielding
    * another function of the same type. The composition should yield None if either function does.
    */
  def compose(g: Double => Option[Double], f: Double => Option[Double]) = (x: Double) => f(x).flatMap(g)

  def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None

  def g(x: Double) = if (x >= 0) Some(Math.sqrt(x)) else None

  val h = compose(g, f) // h(x) should be g(f(x))

  assert(h(2) == Some(1))
  assert(h(1) == None)
  assert(h(0) == None)
}
