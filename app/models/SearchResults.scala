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
  lazy val geoHash: String = GeoHash.encodeHash(lat, lng, Pub.DEFAULT_PUB_SIZE)
}

object Pub {
  implicit val jsonFormat: Format[Pub] = Json.format[Pub]
  val DEFAULT_PUB_SIZE = 7
}

case class PubSeq(pubs: Seq[Pub])

object PubSeq {
  implicit val pubSeqFormat: Format[PubSeq] = Json.format[PubSeq]
}