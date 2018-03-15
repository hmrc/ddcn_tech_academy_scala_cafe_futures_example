package cafe

import java.util.concurrent.Executors

import cafe.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal
import scala.util.{Failure, Random, Success}

case class BrewingException() extends Exception("Water is too cold")

object Cafe extends App {

  private implicit val ec : ExecutionContext = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  def heat(water: Water, temperature : Double = 40D) : Future[Water] = Future {
    Thread.sleep(Random.nextInt(2000))
    water.copy(temperature)
  }

  def grind(beans: CoffeeBeans) : Future[GroundCoffee] = Future.apply {
    Thread.sleep(Random.nextInt(2000))
    println("Grinding")
    GroundCoffee(brand = beans.brand)
  }

  def frothMilk(milk: Milk) : Future[FrothedMilk] = Future {
    require(!milk.equals(SemiSkimmedMilk))
    Thread.sleep(Random.nextInt(2000))
    println("Frothing")
    FrothedMilk(milk.`type`)
  }

  def brew(water: Water, coffee: GroundCoffee) : Future[Coffee] = Future {
    Thread.sleep(Random.nextInt(2000))
    println("Brewing")
    if (water.temperature < 40D) {
      throw BrewingException()
    } else {
     Coffee(water.temperature, coffee)
    }
  }

  val ground = grind(ArabicaBeans)
  val frothed = frothMilk(WholeMilk)
  val heated = heat(Water(), temperature = 60D)

  val brewedCoffee = for {
    ground <- ground
    frothed <- frothed
    water <- heated
    espresso <- brew(water, ground)
  } yield {
    espresso.addMilk(frothed)
  }

  brewedCoffee onComplete {
    case Success(c) =>
      println(s"You have brewed the following coffee $c")
    case Failure(e @ BrewingException()) =>
      println(s"Failed to brew a coffee ${e.getMessage}")
    case Failure(NonFatal(e)) =>
      println(s"Something went wrong! ${e.getMessage}")
  }

}