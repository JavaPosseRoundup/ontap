package models

/**
 * Created by dick on 2/26/14.
 */
sealed abstract class Classification
case object Ale extends Classification
case object Lager extends Classification

case class Beer(name: String,
                 brewery: String,
                 style: String,
                 classification: Classification,
                 ibus: Option[Int] = None,
                 abv: Option[Float] = None)
