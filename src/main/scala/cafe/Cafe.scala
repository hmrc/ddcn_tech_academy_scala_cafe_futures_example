package cafe

object Cafe {

  case class Water(temperature : Double)

  def boil(water: Water, temperature : Double = 40D) = water.copy(temperature)

}
