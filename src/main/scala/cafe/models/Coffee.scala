package cafe.models

sealed trait CoffeeBeans {
  val brand : String
}
case object ArabicaBeans extends CoffeeBeans {
  override val brand: String = "Arabica"
}

case class Coffee(temperature : Double, ground : GroundCoffee, private val milk: Option[Milk] = None) {

  def addMilk(frothedMilk: FrothedMilk) = Coffee(temperature - 5D, ground, Some(frothedMilk))

  def hasMilk : Boolean = milk.isDefined

}

case class GroundCoffee(brand : String)
