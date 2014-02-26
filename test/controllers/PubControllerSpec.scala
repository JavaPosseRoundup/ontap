package controllers

import com.escalatesoft.subcut.inject.NewBindingModule._
import config.StandardBindings
import models._
import org.scalatest.{Matchers, FunSpec}
import play.api.test._
import play.api.test.Helpers._
import services.{FakePubService, PubService}
import com.escalatesoft.subcut.inject.{NewBindingModule, BindingModule}

class PubControllerSpec extends FunSpec with Matchers {

  implicit val testBindings: BindingModule = newBindingModule { module =>
    import module._
    
    module <~ StandardBindings
    bind [PubService] toSingle new FakePubService
  }
  
  describe ("PubController") {
    it ("should list pubs near a lat lon") {
      running(new FakeApplication()) {
        
        val pubController = new PubController
        
        val result = pubController.listNear(0.0F, 0.0F)(FakeRequest())

        status(result) should be (OK)
        contentType(result) should be (Some("application/json"))

        val maybeSearchResults = contentAsJson(result).asOpt[PubSeq]
        
        maybeSearchResults.map(_.pubs.head) should contain (
          Pub("34499325", "Sushi Bar & Grill", "321 Elk Ave", "Crested Butte", "CO", "81224", "US", 38.86972F, -106.98495F)
        )
      }
    }
    
    // todo: test alternate / failure conditions
  }
}
