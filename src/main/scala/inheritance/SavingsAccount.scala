package inheritance

/**
  * 2. Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month. Reset the transaction count in the earnMonthlyInterest method.
  */
class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance: Double) {

  private var count = 0

  override def withdraw(amount: Double): Double = {
    if (count > 2)
      balance -= (amount + 1)
    else
      balance -= amount

    count += 1
    balance
  }

  override def deposit(amount: Double): Double = {
    if (count > 2)
      balance += (amount - 1)
    else
      balance += amount

    count += 1
    balance
  }

  def earnMonthlyInterest(): Unit = {
    if (balance > 0) balance += balance * 0.1
    count = 0
  }

}