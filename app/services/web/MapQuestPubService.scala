package services.web

import services.PubService
import scala.concurrent.Future
import models.{Pub, PubSeq}
import play.api.libs.ws._
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits._



/**
 * Created by dick on 2/26/14.
 */
class MapQuestPubService extends PubService {
  override def near(lat: Float, lng: Float, maybeBeerId: Option[String], scale: Int): Future[PubSeq] =
    MapQuestPubService.pubsForLoc(lat, lng, scale, MapQuestPubService.NUMBER_OF_MATCHES)
}


object MapQuestPubService {
  val NUMBER_OF_MATCHES = 100

  val sizes = Vector(0F, 2500F, 630F, 78F, 20F, 2.4F, 0.61F, 0.076F, 0.019F, 0.001F, 0.0001F, 0.00001F, 0.000001F)

  def serviceURLFor(lat: Float, lng: Float, radius: Float, matches: Int) =
    s"""http://www.mapquestapi.com/search/v2/radius?key=Fmjtd%7Cluur210znl%2C8x%3Do5-90ysqa&maxMatches=$matches&origin=$lat,$lng&hostedData=mqap.ntpois%7Cgroup_sic_code=?%7C581208&radius=$radius&units=k"""

  def pubsForLoc(lat: Float, lng: Float, size: Int, matches: Int): Future[PubSeq] = {
    require(size > 0 && size <= 12, "Size must be between 1 and 12")
    val respFuture = WS.url(serviceURLFor(lat, lng, sizes(size), matches)).get()

    for(response <- respFuture) yield {
      val json = response.json
      PubSeq((json \\ "fields").map { json =>
        json.asOpt[Pub]
      }.flatten)
    }
  }
}