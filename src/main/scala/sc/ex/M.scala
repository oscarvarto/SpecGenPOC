package sc.ex

object M:
  case class Address(street: String, zipCode: String)
  
  sealed trait People
  case class Person(val name: String,val age: Int, val address: Address) extends People
  
  case class Gamer(
    name: String,
    age: Int,
    address: Address,
    favoriteGame: String
  ) extends People
  
export M.*
