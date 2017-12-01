package inheritance

class BankAccount(initialBalance: Double) {
  protected var balance: Double = initialBalance

  def currentBalance: Double = balance

  def deposit(amount: Double): Double = {
    balance += amount
    balance
  }

  def withdraw(amount: Double): Double = {
    balance -= amount
    balance
  }
}