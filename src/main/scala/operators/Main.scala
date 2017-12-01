package operators

import scala.collection.mutable.ArrayBuffer
import scala.language.dynamics

object Main extends App {
  //  val f = Fraction(3, 2)
  //  val g = Fraction(5, -3)
  //  println(f+g)
  //  println(f-g)
  //  println(f*g)
  //  println(f/g)
  //  println(Money(1, 75) + Money(0, 50))

  //  val t = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
  //  println(t)

  //  val a = ArrayBuffer("/\\_/\\", "( ' ' )", "(  -  )", "| | |", "(__|__)")
  //  val b = ArrayBuffer("-----", "/ Hello \\", "<  Scala |", "\\ Coder /", "-----")
  //
  //  val artLeft = new ASCIIArt(a)
  //  val artRight = new ASCIIArt(b)
  //
  //  println(artLeft | artRight)

  //  val A = Matrix(Array(Array(1,2,3), Array(4,5,6)))
  //  val B = Matrix(Array(Array(7,8), Array(9,10), Array(11,12)))
  //
  //  println(A * B)
  //  val m = A * 2
  //  println(m(1, 2))

  //  val filepath = "/home/cay/readme.txt"
  //
  //  filepath match {
  //    case PathComponents(dir, name) => "A"
  //    case PathComponents(dir, dir2, dir3) => "B"
  //    case _ => "C"
  //  }
  //
  //  val dynamicProps = new DynamicProps(System.getProperties)
  //  println(dynamicProps.java.home)
  //  dynamicProps.moj.propy = "hehe"
  //  println(dynamicProps.moj.propy)

  //  val xmlBuilder = new XmlBuilder
  //  println(xmlBuilder.ul(id="42", style="list-style: lower-alpha;").li(id="32"))

  val xml =
    """
      <messages>
        <note id="501">
          <to>Tove</to>
          <from>Jani</from>
          <heading>Reminder</heading>
          <body>Don't forget me this weekend!</body>
       </note>
        <note id="502">
          <to>Jani</to>
          <from>Tove</from>
          <heading>Re: Reminder</heading>
          <body>I will not</body>
        </note>
      </messages>"""

  val xmlElement = new XmlElement(xml)
  println(xmlElement.messages.note(id = "502").heading)
}
