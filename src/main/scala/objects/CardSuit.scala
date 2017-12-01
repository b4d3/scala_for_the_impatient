package objects

/**
  * 6. Write an enumeration describing the four playing card suits so that the toString method returns ♣, ♦, ♥, or ♠.
  */
object CardSuit extends Enumeration {
  val ♦, ♣, ♥, ♠ = Value

  def isRed(card: CardSuit.Value): Boolean = card == CardSuit.♦ || card == CardSuit.♥
}

