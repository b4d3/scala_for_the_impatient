package objects

/**
  * 2. Provide a general superclass UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and MilesToKilometers that extend it.
  */
class UnitConversions {
  def inchesToCentimeters(inches: Double): Double = inches * 2.54

  def gallonToLiter(gallon: Double): Double = gallon * 3.78

  def milesToKilometers(miles: Double): Double = miles * 1.6
}

object InchesToCentimeters extends UnitConversions

object GallonToLiter extends UnitConversions

object MilesToKilometers extends UnitConversions
