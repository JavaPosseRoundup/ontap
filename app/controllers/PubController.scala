package controllers

import play.api.mvc._
import play.api.Play.current
import scala.concurrent.Future
import play.api.libs.json.Json
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import models._
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import services.PubService

class PubController(implicit val bindingModule: BindingModule) 
  extends Controller with Injectable {

  val pubService = inject[PubService]
  
  implicit val askTimeout = Timeout(15.seconds)
  
  def listNear(lat: Float, lng: Float, scale: Int) = Action.async {
    //val geo = Geolocation("", lat, lon)
    
    val pubSeqFuture = pubService.near(lat, lng, scale)
    
    pubSeqFuture.map { pubSeq =>
      Ok(Json.toJson(pubSeq))
    }
    
    /*
    val beerSearchActorRef: ActorRef = Akka.system.actorOf(Props[BeerSearchActor])

    val searchResults: Future[Any] = beerSearchActorRef ? DoSearch(query, location)
    
    val resultFuture: Future[SimpleResult] = searchResults.map {
      case searchResults: PubSeq =>
        Ok(Json.toJson(searchResults))
      case _ =>
        InternalServerError("Unknown Error")
    } recover {
      case e: Exception =>
        InternalServerError(e.toString)
    }
    
    resultFuture
    */
  }

}