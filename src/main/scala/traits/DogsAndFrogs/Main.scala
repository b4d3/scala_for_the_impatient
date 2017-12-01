package traits.DogsAndFrogs

object Main extends App {

  val d = new Dog
  d.express()
  d.jump("stone")

  val f = new Frog {
    isWalking = false
  }
  f.express()
  f.jump("stone")

}
