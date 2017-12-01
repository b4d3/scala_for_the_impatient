package classes

/**
  * 2. Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.
  */
class BankAccount(startingBalance: Int = 0) {

  private var _balance: Int = startingBalance

  def deposit(money: Int): Unit = {
    _balance += money
  }

  def withdraw(money: Int): Unit = {
    if (money > _balance)
      println("Insufficient funds")
    else
      _balance -= money
  }

  def balance: Int = _balance
}
