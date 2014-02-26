import models._
import org.scalatest._
import org.scalatest.matchers._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

class SearchControllerSpec extends FunSpec with Matchers {

  describe ("Application") {
    it ("should do a search with a query and location are specified") {
      running(new FakeApplication()) {
        val result = controllers.Application.doSearch("foo", "asdf")(FakeRequest())

        status(result) should be (OK)
        contentType(result) should be (Some("application/json"))

        val maybeSearchResults = contentAsJson(result).asOpt[PubSeq]

        maybeSearchResults should be (
          Some(
            PubSeq(Seq(Pub("2406", "Two Bells", "Beer Bar", "2313 4th Ave", "Seattle", "WA", "98121", "United States")))
          )
        )
      }
    }
    
    // todo: test alternate / failure conditions
  }
}
