package models

import cafe.Cafe.{Coffee, FrothedMilk, GroundCoffee}
import org.scalatest.{MustMatchers, WordSpec}

class CoffeeSpec extends WordSpec with MustMatchers {

  "Coffee" should {

    "be able to have milk added and reduce the temperature 5 degrees" in {
      val coffeeWithoutMilk = Coffee(temperature = 40D, ground = GroundCoffee("Arrabica"))
      val coffeeWithMilk = coffeeWithoutMilk.addMilk(FrothedMilk("Whole"))
      coffeeWithMilk.hasMilk mustBe true
      coffeeWithMilk.temperature mustBe 35D
    }

  }

}
