package actors

import akka.actor.Actor
import play.api.libs.json.{Json, Format}
import models._

class BeerSearchActor extends Actor {

  def receive = {
    case doSearch: DoSearch =>
      context.system.log.info(doSearch.toString)
      sender ! PubSeq(Seq(Pub("2406", "Two Bells", "Beer Bar", "2313 4th Ave", "Seattle", "WA", "98121", "United States")))
    case _ =>
      context.system.log.info("do what now?")
  }
  
}

case class DoSearch(query: String, location: String)
