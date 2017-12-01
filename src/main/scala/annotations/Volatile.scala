package annotations

/**
  * 6. Write a Scala object with a volatile Boolean field. Have one thread sleep for some time,
  * then set the field to true, print a message, and exit. Another thread will keep checking
  * whether the field is true. If so, it prints a message and exits. If not, it sleeps for a
  * short time and tries again. What happens if the variable is not volatile?
  */
object Volatile extends App {

  @volatile var flag = false

  new Thread(() => {
    Thread.sleep(3000)
    flag = true
    println("Set to true")
  }).start()

  new Thread(() => {
    while (!flag) {
      Thread.sleep(1000)
    }
    println("Read true!")
  }).start()
}
