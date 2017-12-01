package traits

/**
  * Created by bade on 08.07.17..
  */
trait PropertyChangeSupport {
  val pcs = new java.beans.PropertyChangeSupport(this)
}
