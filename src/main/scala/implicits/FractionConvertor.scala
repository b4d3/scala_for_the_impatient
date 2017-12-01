package implicits

import operators.Fraction

object FractionConvertor {

  implicit class RichFraction(fraction: Fraction) extends Ordered[Fraction] {

    implicit def fraction2OrderedFraction(fraction: Fraction): Ordered[Fraction] = this

    override def compare(that: Fraction): Int = {
      val ret = (fraction.n.asInstanceOf[Double] / fraction.d) - (that.n.asInstanceOf[Double] / that.d)

      if (ret < 0) -1
      else if (ret > 0) 1
      else 0
    }
  }
}
