package controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Akka
import akka.pattern.ask
import akka.actor.{ActorRef, Props}
import play.api.Play.current
import scala.concurrent.Future
import play.api.libs.json.Json
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import models._
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}

class Application(implicit val bindingModule: BindingModule) 
  extends Controller with Injectable {

  //implicit val askTimeout = Timeout(15.seconds)
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  /*
  def doSearch(query: String, location: String) = Action.async {
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
  }
  */

}