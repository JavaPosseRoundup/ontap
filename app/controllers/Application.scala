package controllers

import play.api.mvc._
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
