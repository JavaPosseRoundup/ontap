package controllers

import com.escalatesoft.subcut.inject.NewBindingModule._
import config.StandardBindings
import models._
import org.scalatest.{Matchers, FunSpec}
import play.api.test._
import play.api.test.Helpers._
import services.{FakePubService, PubService}
import com.escalatesoft.subcut.inject.{NewBindingModule, BindingModule}
import services.web.MapQuestPubService

class PubControllerSpec extends FunSpec with Matchers {

  implicit val testBindings: BindingModule = newBindingModule { module =>
    import module._
    
    module <~ StandardBindings
    bind [PubService] toSingle new FakePubService
  }
  
  describe ("PubController") {
    describe ("near") {
      it ("should not return any pubs near James's house") {
        running(new FakeApplication()) {
           
          val pubController = new PubController
            
          val (lat, lng) = (38.867183F, -106.976208F)
            
          val result = pubController.near(lat, lng, None, 7)(FakeRequest())
    
          status(result) should be (OK)
          contentType(result) should be (Some("application/json"))
  
          val maybeSearchResults = contentAsJson(result).asOpt[PubSeq]
          
          maybeSearchResults.get.pubs should be (empty)
        }
      }
      it ("should return a pub at a known pub") {
        running(new FakeApplication()) {
  
          val pubController = new PubController
          
          val (lat, lng) = (38.86972F, -106.98698F)
          
          val result = pubController.near(lat, lng, None, Pub.DEFAULT_BOTTLE_SIZE)(FakeRequest())
  
          status(result) should be (OK)
          contentType(result) should be (Some("application/json"))
  
          val maybeSearchResults = contentAsJson(result).asOpt[PubSeq]

          maybeSearchResults.get.pubs.length should be (1)
          
          maybeSearchResults.get.pubs.head should be (
            Pub("17175157", "Bacchanale", "209 Elk Ave", "Crested Butte", "CO", "81224", "US", lat, lng)
          )

        }
      }
      it ("should return a list of pubs in Crested Butte") {
        running(new FakeApplication()) {

          val pubController = new PubController

          val (lat, lng) = (38.86972F, -106.98698F)

          val result = pubController.near(lat, lng, None, Pub.DEFAULT_TOWN_SIZE)(FakeRequest())

          status(result) should be (OK)
          contentType(result) should be (Some("application/json"))

          val maybeSearchResults = contentAsJson(result).asOpt[PubSeq]

          maybeSearchResults.get.pubs should contain (
            Pub("17175157", "Bacchanale", "209 Elk Ave", "Crested Butte", "CO", "81224", "US", lat, lng)
          )

          maybeSearchResults.get.pubs.length should be > 1
        }
      }
      it ("should return a list of pubs in Crested Butte that have Pliny") {
        running(new FakeApplication()) {

          val pubController = new PubController

          val (lat, lng) = (38.86972F, -106.98698F)
          
          val plinyId = "1"

          val result = pubController.near(lat, lng, Some(plinyId), Pub.DEFAULT_TOWN_SIZE)(FakeRequest())

          status(result) should be (OK)
          contentType(result) should be (Some("application/json"))

          val maybeSearchResults = contentAsJson(result).asOpt[PubSeq]

          maybeSearchResults.get.pubs should contain (
            Pub("17233797", "Brick Oven Pizzeria", "229 Elk Ave", "Crested Butte", "CO", "81224", "US", 38.869723F, -106.987009F)
          )

          maybeSearchResults.get.pubs.length should be >= 1
        }
      }
    }
    
    // todo: test alternate / failure conditions
  }
}
