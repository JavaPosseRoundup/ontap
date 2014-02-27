package services

import models._
import scala.concurrent.Future
import models.Beer

trait BeerService {
  def beersForPub(pubId: PubId): Future[Seq[Beer]]
}

class FakeBeerService extends BeerService {
  override def beersForPub(pubId: PubId): Future[Seq[Beer]] =
    pubId match {
      case DiveBar =>
        Future.successful(Seq.empty)
      case BrickOvenPizza =>
        val pliny = Beer(
          "Pliny",
          "Russian River Brewery",
          "Double IPA",
          Ale
        )
        Future.successful(Seq(pliny))
    }
}
