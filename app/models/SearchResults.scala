package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

/*
case class Geolocation(geoHash: String,
                       lat: Float,
                       lon: Float)


object Geolocation {
  def latLonToGeo(lat: Float, lon: Float) = {
    Geolocation("", lat, lon)
  }
  
  def geoToLatLon(geo: Geolocation) = {
    (geo.lat, geo.lon)
  }
  
  implicit def geolocationFormat: Format[Geolocation] = (
    (__ \ "lat").format[Float] ~
    (__ \ "lng").format[Float]
  )(latLonToGeo, geoToLatLon)
}
*/

case class Pub(id: String,
               name: String,
               address: String,
               city: String,
               state: String,
               postal_code: String,
               country: String,
               lat: Float,
               lng: Float) {

}

object Pub {
  implicit val jsonFormat: Format[Pub] = Json.format[Pub]
}

case class PubSeq(pubs: Seq[Pub])

object PubSeq {
  implicit val pubSeqFormat: Format[PubSeq] = Json.format[PubSeq]
}