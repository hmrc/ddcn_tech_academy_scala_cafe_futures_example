package cafe

import cafe.models._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Cafe extends App {

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


  val ground = grind(ArabicaBeans)
  val frothed = frothMilk(WholeMilk)
  val heated = heat(Water())

  val brewedCoffee = for {
    ground <- ground
    frothed <- frothed
    water <- heated
    espresso <- brew(water, ground)
  } yield espresso.addMilk(frothed)

  brewedCoffee onComplete {
    case Success(c) =>
      println(s"You have brewed the following coffee $c")
    case Failure(e) =>
      println(s"Failed to brew a coffee ${e.getMessage}")
  }

}