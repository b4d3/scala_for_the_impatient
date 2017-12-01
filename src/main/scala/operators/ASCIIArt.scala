package operators

import scala.collection.mutable.ArrayBuffer

class ASCIIArt(val art: ArrayBuffer[String]) {

  override def toString: String = art.mkString("\n")

  def -(other: ASCIIArt): ASCIIArt = {
    val combined = this.art zip other.art
    val combinedString = combined.map(s => s._1 + " " + s._2)
    new ASCIIArt(combinedString)
  }

  def |(other: ASCIIArt): ASCIIArt = new ASCIIArt(this.art ++ other.art)

}
