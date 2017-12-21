package cafe

import cafe.Cafe._
import org.scalatest.{MustMatchers, WordSpec}

class CafeSpec extends WordSpec with MustMatchers {

  "Cafe" when {

    "preparing water" should {

      "boil water to 40 degrees as default" in {
        val water = Water(temperature = 0)
        val boiled = Cafe.heat(water)
        boiled.temperature mustEqual 40D
      }

      "boil water to 50 degrees" in {
        val temperature = 50D
        val water = Water(temperature = 0)
        val boiled = Cafe.heat(water, temperature)
        boiled.temperature mustEqual 50D
      }

    }

    "preparing coffee beans" should {

      "return GroundCoffee when provided CoffeeBeans" in {
        val beans = ArrabicaBeans
        val ground = Cafe.grind(beans)
        ground mustBe a[GroundCoffee]
        ground.brand mustBe "Arrabica"
      }

    }

    "frothing milk" should {

      "froth WholeMilk" in {
        val wholeMilk = WholeMilk
        val frothed = Cafe.frothMilk(wholeMilk)
        frothed mustBe a[FrothedMilk]
        frothed.`type` mustBe "Whole"
      }

      "not froth semi skimmed milk" in {
        intercept[IllegalArgumentException] {
          val semi = SemiSkimmedMilk
          Cafe.frothMilk(semi)
        }
      }
      
    }

  }

}