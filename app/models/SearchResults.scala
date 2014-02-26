package models

import play.api.libs.json.{Json, Format}
import models.xmlutil.XmlFormat
import scala.xml.Node

case class Geolocation(geoHash: String,
                       lat: Float,
                       long: Float)

object Geolocation {
  implicit def geolocationFormat: Format[Geolocation] = Json.format[Geolocation]
}

case class Pub(id: String,
               name: String,
               status: String,
               street: String,
               city: String,
               state: String,
               zip: String,
               country: String,
               geoLocation: Option[Geolocation] = None) {

}

object Pub {
  implicit val jsonFormat: Format[Pub] = Json.format[Pub]

  implicit val xmlFormat = new XmlFormat[Pub] {
    override def writeXml(item: Pub): Node = {
      <location>
        <id>{item.id}</id>
        <name>{item.name}</name>
        <status>{item.status}</status>
        <reviewlink></reviewlink>
        <proxylink></proxylink>
        <blogmap></blogmap>
        <street>{item.street}</street>
        <city>{item.city}</city>
        <state>{item.state}</state>
        <zip>{item.zip}</zip>
        <country>{item.country}</country>
        <phone></phone>
        <overall></overall>
        <imagecount></imagecount>
      </location>
    }

    override def readXml(node: Node): Pub = {
      val id = (node \ "id").text
      val name = (node \ "name").text
      val status = (node \ "status").text
      val street = (node \ "street").text
      val city = (node \ "city").text
      val state = (node \ "state").text
      val zip = (node \ "zip").text
      val country = (node \ "country").text
      Pub(id, name, status, street, city, state, zip, country)
    }
  }
}

case class PubSeq(pubs: Seq[Pub])

object PubSeq {
  implicit val pubSeqFormat: Format[PubSeq] = Json.format[PubSeq]
}