package cafe

import cafe.models._
import org.scalactic.source.Position
import org.scalatest.{AsyncWordSpec, MustMatchers}

import scala.concurrent.Future
import scala.reflect.ClassTag

class CafeSpec extends AsyncWordSpec with MustMatchers {

  "Cafe" when {

    "preparing water" should {

      "boil water to 40 degrees as default" in {
        val water = Water(temperature = 0)
        val boiled = Cafe.heat(water)
        boiled.map { water => assert(water.temperature == 40D) }(ec)
      }

      "boil water to 50 degrees" in {
        val temperature = 50D
        val water = Water(temperature = 0)
        val boiled = Cafe.heat(water, temperature)
        boiled.map { water => assert(water.temperature == 50D) }(ec)
      }

    }

    "preparing coffee beans" should {

      "return GroundCoffee when provided CoffeeBeans" in {
        val beans = ArabicaBeans
        val ground = Cafe.grind(beans)
        ground.map { g => assert(g.brand == "Arabica")}(ec)
      }

    }

    "frothing milk" should {

      "froth WholeMilk" in {
        val wholeMilk = WholeMilk
        val frothed = Cafe.frothMilk(wholeMilk)
        frothed.map { m => assert(m.`type` == "Whole")}(ec)
      }

      "not froth semi skimmed milk" in {
        val semi = SemiSkimmedMilk
        recoverToSucceededIf[IllegalArgumentException] { Cafe.frothMilk(semi) }(implicitly[ClassTag[IllegalArgumentException]], ec, implicitly[Position])
      }

    }

    "combining heated water and ground coffee" should {

      "brew the coffee" in {
        val heatedWater = Water(temperature = 40D)
        val groundCoffee = GroundCoffee("Arabica")
        val brew = Cafe.brew(heatedWater, groundCoffee)
        brew.map { b =>
          b mustBe (b.temperature == 40D)
          b mustBe (b.ground.brand == "Arabica")
        }(ec)
      }

      "throw BrewingException when the temperature is less than 40D" in {
        val heatedWater = Water(temperature = 39D)
        val groundCoffee = GroundCoffee("Arabica")
        val brew = Cafe.brew(heatedWater, groundCoffee)
        val ex: Future[BrewingException] = recoverToExceptionIf[BrewingException] { brew }(implicitly[ClassTag[BrewingException]], ec, implicitly[Position])
        ex.map { e => e.getMessage mustBe "Water is too cold"  }(ec)
      }

    }

  }

}