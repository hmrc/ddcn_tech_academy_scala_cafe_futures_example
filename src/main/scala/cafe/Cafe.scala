package cafe

import cafe.models._

object Cafe {

  def heat(water: Water, temperature : Double = 40D) : Water = water.copy(temperature)

  def grind(beans: CoffeeBeans) : GroundCoffee = GroundCoffee(brand = beans.brand)

  def frothMilk(milk: Milk) : FrothedMilk = {
    require(!milk.equals(SemiSkimmedMilk))
    FrothedMilk(milk.`type`)
  }

  case class BrewingException() extends Exception("Water is too cold")

  def brew(water: Water, coffee: GroundCoffee) : Coffee = {
    if (water.temperature < 40D) {
      throw BrewingException()
    } else {
      Coffee(water.temperature, coffee)
    }
  }

}