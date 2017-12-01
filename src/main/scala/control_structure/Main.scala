package control_structure

import java.time.LocalDate

object Main {

  /**
    * 11. Define a string interpolator date so that you can define a java.time.LocalDate as date"$year-$month-$day".
    */
  implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
    def date(args: Any*): LocalDate = {
      val year: Int = args(0).toString.toInt
      val month: Int = args(1).toString.toInt
      val day: Int = args(2).toString.toInt

      LocalDate.of(year, month, day)
    }
  }

}
