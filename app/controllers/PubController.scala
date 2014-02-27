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
import services.web.MapQuestPubService

class PubController(implicit val bindingModule: BindingModule) 
  extends Controller with Injectable {

  lazy val mapQuestPubService = new MapQuestPubService

  val pubService = injectOptional [PubService] getOrElse mapQuestPubService
  
  implicit val askTimeout = Timeout(15.seconds)
  
  def near(lat: Float, lng: Float, beerId: Option[String], scale: Int) = Action.async {
    val pubSeqFuture = pubService.near(lat, lng, beerId, scale)
    
    pubSeqFuture.map { pubSeq =>
      
      Ok(Json.toJson(pubSeq))
    }
  }

  def nearTest(lat: Float, lng: Float, beerId: Option[String], scale: Int) = Action.async {
    val pubSeqFuture = pubService.near(lat, lng, beerId, scale)

    pubSeqFuture.map { pubSeq =>

      Ok(views.html.pubstest(pubSeq))
    }
  }
  
  def beers(id: String) = Action.async {
    
    //pubService.beers(id)
    
    Future.successful(NotImplemented)
  }

  def beerAtPub(pubId: String, beerId: String) = play.mvc.Results.TODO

  def beerNotAtPub(pubId: String, beerId: String) = play.mvc.Results.TODO
}