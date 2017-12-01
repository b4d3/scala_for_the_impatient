package inheritance

/**
  * 1. Extend the BankAccount class to a CheckingAccount class that charges $1 for every deposit and withdrawal.
  */
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance: Double) {

  override def deposit(amount: Double): Double = super.deposit(amount - 1)

  override def withdraw(amount: Double): Double = super.withdraw(amount - 1)
}