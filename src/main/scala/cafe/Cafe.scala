package cafe

object Cafe {

  case class Water(temperature : Double)

  def heat(water: Water, temperature : Double = 40D) : Water = water.copy(temperature)


  sealed trait CoffeeBeans {
    val brand : String
  }
  case object ArrabicaBeans extends CoffeeBeans {
    override val brand: String = "Arrabica"
  }

  case class GroundCoffee(brand : String)

  def grind(beans: CoffeeBeans) : GroundCoffee = GroundCoffee(brand = beans.brand)

}