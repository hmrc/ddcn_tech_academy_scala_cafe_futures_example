package cafe

import cafe.Cafe.Water
import org.scalatest.{MustMatchers, WordSpec}

class CafeSpec extends WordSpec with MustMatchers {

  "Cafe" when {

    "preparing water" should {

      "boil water to 40 degrees" in {
        val temperature = 40D
        val water = Water(temperature = 0)
        val boiled = Cafe.boil(water)
        boiled.temperature mustEqual 40D
      }

    }

  }

}