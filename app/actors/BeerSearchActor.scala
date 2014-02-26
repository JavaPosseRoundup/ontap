package actors

import akka.actor.Actor
import play.api.libs.json.{Json, Format}

class BeerSearchActor extends Actor {

  def receive = {
    case doSearch: DoSearch =>
      context.system.log.info(doSearch.toString)
      sender ! SearchResults("asdf")
    case _ =>
      context.system.log.info("hello")
  }
  
}

case class DoSearch(query: String, location: String)

case class SearchResults(foo: String)

object SearchResults {
  implicit val format: Format[SearchResults] = Json.format[SearchResults]
}