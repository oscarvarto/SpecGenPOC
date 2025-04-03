package sc.ex

object M {
  sealed trait People
  case class Person(name: String, age: Int) extends People
}  

export M.*
