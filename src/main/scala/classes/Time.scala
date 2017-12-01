package classes

/**
  * 4. Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public interface. That is, client code should be unaffected by your change.
  */
class Time(hrs: Int = 0, min: Int = 0) {

  val hours: Int = hrs
  val minutes: Int = min

  def before(other: Time): Boolean = (hours < other.hours) || (hours == other.hours && minutes < other.minutes)
}



