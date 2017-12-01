package operators

class Money(w: Int, p: Int) {

  private val whole = if (p >= 100) w + 1 else w
  private val part = if (p >= 100) p - 100 else p

  override def toString: String = s"$$$whole.$part"

  def +(other: Money) = Money(whole + other.whole, part + other.part)

  def -(other: Money) = Money(whole + other.whole, part + other.part)
}

object Money {
  def apply(whole: Int, part: Int): Money = new Money(whole, part)
}
