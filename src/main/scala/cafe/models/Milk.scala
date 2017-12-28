package cafe.models

sealed trait Milk {
  val `type` : String
}
case object WholeMilk extends Milk {
  override val `type` : String = "Whole"
}
case object SemiSkimmedMilk extends Milk {
  override val `type` : String = "SemiSkimmed"
}

case class FrothedMilk(`type`: String) extends Milk
