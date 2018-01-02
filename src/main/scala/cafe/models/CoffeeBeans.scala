package cafe.models

sealed trait CoffeeBeans {
  val brand : String
}
case object ArabicaBeans extends CoffeeBeans {
  override val brand: String = "Arabica"
}