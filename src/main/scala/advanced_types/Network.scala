package advanced_types

import scala.collection.mutable.ArrayBuffer

class Network {

  class Member(val name: String) {

    val outer: Network = Network.this
    val contacts = new ArrayBuffer[Network#Member]

    /**
      * 4. Implement the equals method for the Member class that is nested inside the Network
      * class in Section 19.2, “Type Projections,” on page 281. For two members to be equal, they
      * need to be in the same network.
      */
    override def equals(other: Any): Boolean = other match {
      case _: Member => true
      case _ => false
    }
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String): Member = {
    val m = new Member(name)
    members += m
    m
  }

}
