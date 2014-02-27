package controllers

import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.json.Json
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import services.PubLocatorService
import services.web.MapQuestPubLocatorService

class PubController(implicit val bindingModule: BindingModule) 
  extends Controller with Injectable {

  val pubService = injectOptional [PubLocatorService] getOrElse MapQuestPubLocatorService
  
  implicit val askTimeout = Timeout(15.seconds)
  
  def near(lat: Float, lng: Float, beerId: Option[String], scale: Int) = Action.async {
    val pubSeqFuture = pubService.near(lat, lng, beerId, scale)
    
    pubSeqFuture.map { pubSeq =>
      
      Ok(Json.toJson(pubSeq))
    }
  }
  
  def beers(id: String) = Action.async {
    Future.successful(NotImplemented)
  }

  def beerAtPub(pubId: String, beerId: String) = play.mvc.Results.TODO

  def beerNotAtPub(pubId: String, beerId: String) = play.mvc.Results.TODO
}
