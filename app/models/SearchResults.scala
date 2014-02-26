package models

import play.api.libs.json.{Json, Format}
import models.xmlutil.XmlFormat
import scala.xml.Node

case class SearchResult(id: String, name: String, status: String, street: String, city: String, state: String, zip: String, country: String)

object SearchResult {
  implicit val jsonFormat: Format[SearchResult] = Json.format[SearchResult]

  implicit val xmlFormat = new XmlFormat[SearchResult] {
    override def writeXml(item: SearchResult): Node = {
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

    override def readXml(node: Node): SearchResult = {
      val id = (node \ "id").text
      val name = (node \ "name").text
      val status = (node \ "status").text
      val street = (node \ "street").text
      val city = (node \ "city").text
      val state = (node \ "state").text
      val zip = (node \ "zip").text
      val country = (node \ "country").text
      SearchResult(id, name, status, street, city, state, zip, country)
    }
  }
}