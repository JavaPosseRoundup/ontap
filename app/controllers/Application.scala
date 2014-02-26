package controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Akka
import actors.BeerSearchActor
import akka.actor.Props
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    val beerSearchActorRef = Akka.system.actorOf(Props[BeerSearchActor])
    
    beerSearchActorRef ! "a message"
    
    Ok(views.html.index("Your new application is ready."))
  }

}