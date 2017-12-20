package cafe

object Cafe {

  case class Water(temperature : Double)

  def boil(water: Water) = water.copy(40D)

}
