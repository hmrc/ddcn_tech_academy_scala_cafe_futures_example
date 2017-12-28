package cafe

import cafe.models._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Cafe {

  def heat(water: Water, temperature : Double = 40D) : Future[Water] = Future { water.copy(temperature) }

  def grind(beans: CoffeeBeans) : Future[GroundCoffee] = Future { GroundCoffee(brand = beans.brand) }

  def frothMilk(milk: Milk) : Future[FrothedMilk] = Future {
    require(!milk.equals(SemiSkimmedMilk))
    FrothedMilk(milk.`type`)
  }

  case class BrewingException() extends Exception("Water is too cold")

  def brew(water: Water, coffee: GroundCoffee) : Future[Coffee] = Future {
    if (water.temperature < 40D) {
      throw BrewingException()
    } else {
     Coffee(water.temperature, coffee)
    }
  }

}