package operators

class Matrix(val m: Array[Array[Int]]) {

  def apply(row: Int, column: Int): Int = m(row)(column)

  def update(row: Int, column: Int, value: Int) = m(row)(column) = value

  def +(other: Matrix): Matrix = {
    var sumArray = new Array[Array[Int]](m.length)

    for (i <- m.indices) {
      var sumRow = for (j <- m(0).indices) yield m(i)(j) + other.m(i)(j)
      sumArray(i) = sumRow.toArray
    }
    Matrix(sumArray)
  }

  def *(other: Matrix): Matrix = {
    if (m.length != other.m(0).length && m(0).length != other.m.length)
      throw new IllegalArgumentException("Cannot multiply")
    else {
      var sumArray = new Array[Array[Int]](m.length)

      for (i <- m.indices) {
        sumArray(i) = new Array[Int](m.length)
        for (j <- m.indices) {
          var mulRow = for (k <- m(0).indices) yield m(i)(k) * other.m(k)(j)
          sumArray(i)(j) = mulRow.sum
        }
      }
      Matrix(sumArray)
    }
  }

  def *(scalar: Int): Matrix = Matrix(m.map(_.map(_ * scalar)))

  override def toString = m.map(_.mkString(" ")).mkString("\n")
}

object Matrix {
  def apply(m: Array[Array[Int]]): Matrix = {
    if (m.isEmpty)
      throw new IllegalArgumentException("Matrix must have at least one row")
    else {
      val numberOfColumns = m(0).length
      for (r <- m) if (r.length != numberOfColumns) throw new IllegalArgumentException("Matrix must have equal number of columns")
      new Matrix(m)
    }
  }
}