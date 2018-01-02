package cafe.models

case class Coffee(temperature : Double, ground : GroundCoffee, private val milk: Option[Milk] = None) {

  def addMilk(frothedMilk: FrothedMilk) = Coffee(temperature - 5D, ground, Some(frothedMilk))

  def hasMilk : Boolean = milk.isDefined

  override def toString: String = {
    val withMilk = if(hasMilk) s"with ${milk.get.`type`} milk" else "without milk"
    s"Coffee at $temperature degrees $withMilk"
  }

}