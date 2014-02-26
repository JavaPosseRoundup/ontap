package actors

import akka.actor.Actor

class BeerSearchActor extends Actor {

  def receive = {
    case _ => context.system.log.info("hello")
  }
  
}
