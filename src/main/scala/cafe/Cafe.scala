package cafe

object Cafe {

  case class Water(temperature : Double = 0D)

  def heat(water: Water, temperature : Double = 40D) : Water = water.copy(temperature)


  sealed trait CoffeeBeans {
    val brand : String
  }
  case object ArrabicaBeans extends CoffeeBeans {
    override val brand: String = "Arrabica"
  }

  case class Coffee(temperature : Double, beans : String)
  case class GroundCoffee(brand : String)

  def grind(beans: CoffeeBeans) : GroundCoffee = GroundCoffee(brand = beans.brand)


  sealed trait Milk {
    val `type` : String
  }
  case object WholeMilk extends Milk {
    override val `type` : String = "Whole"
  }
  case object SemiSkimmedMilk extends Milk {
    override val `type` : String = "SemiSkimmed"
  }

  case class FrothedMilk(`type`: String)

  def frothMilk(milk: Milk) : FrothedMilk = {
    require(!milk.equals(SemiSkimmedMilk))

    FrothedMilk(milk.`type`)
  }

  case class BrewingException() extends Exception("Water is too cold")

  def brew(water: Water, coffee: GroundCoffee) : Coffee = {
    if (water.temperature < 40D) {
      throw BrewingException()
    } else {
      Coffee(water.temperature, coffee.brand)
    }
  }

}