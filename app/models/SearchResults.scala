package models

import play.api.libs.json._
import com.github.davidmoten.geo.GeoHash

case class Pub(id: String,
               name: String,
               address: String,
               city: String,
               state: String,
               postal_code: String,
               country: String,
               lat: Float,
               lng: Float) {
  lazy val geoHash: String = GeoHash.encodeHash(lat, lng, Pub.DEFAULT_BOTTLE_SIZE)
}

object Pub {
  implicit val jsonFormat: Format[Pub] = Json.format[Pub]
  val DEFAULT_TOWN_SIZE = 5
  val DEFAULT_BLOCK_SIZE = 6
  val DEFAULT_PUB_SIZE = 7
  val DEFAULT_BOTTLE_SIZE = 12
}

case class PubSeq(pubs: Seq[Pub])

object PubSeq {
  implicit val pubSeqFormat: Format[PubSeq] = Json.format[PubSeq]
}

/**
 * Unique Pub Id
 */
sealed trait PubId {
  /**
   * String value of the Pub Id
   */
  val id: String
}

object DiveBar extends PubId {
  override val id: String = "Dive Bar"
}

object BrickOvenPizza extends PubId {
  override val id: String = "Brick Oven Pizza"
}

/**
 * Pub Id that has been retrieved from a backend service or data store.
 */
case class RetrievedPubId(id: String) extends PubId
