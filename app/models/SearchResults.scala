package models

import play.api.libs.json.{Json, Format}

case class SearchResult(id: String, name: String, status: String, street: String, city: String, state: String, zip: String, country: String)

object SearchResult {
  implicit val format: Format[SearchResult] = Json.format[SearchResult]
}