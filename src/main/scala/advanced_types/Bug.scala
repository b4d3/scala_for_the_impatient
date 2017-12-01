package advanced_types

object Then

object Around

object Show

/**
  * ~ 1. Implement a Bug class modeling a bug that moves along a horizontal line. The move method
  * moves in the current direction, the turn method makes the bug turn around, and the show
  * method prints the current position. Make these methods chainable. ~
  *
  * 2. Provide a fluent interface for the Bug class of the preceding exercise, so that one can write
  *
  *     bugsy move 4 and show and then move 6 and show turn around move 5 and show
  */
class Bug {

  private var position = 0
  private var direction = 1

  def and(obj: Show.type): this.type = {
    show
  }

  def and(obj: Then.type): this.type = this

  def show: this.type = {
    println(position)
    this
  }

  def move(by: Int): this.type = {
    position += by * direction
    this
  }

  def turn(): this.type = {
    direction *= -1
    this
  }

  def turn(around: Around.type): this.type = turn()
}
