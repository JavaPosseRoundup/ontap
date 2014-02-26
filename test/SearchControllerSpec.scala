import models.SearchResult
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class SearchControllerSpec extends Specification {

  "Application" should {

    "do a search with a query and location are specified" in new WithApplication {
      val result = controllers.Application.doSearch("foo", "asdf")(FakeRequest())
      
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      
      val maybeSearchResults = contentAsJson(result).asOpt[Seq[SearchResult]]
      
      maybeSearchResults must beSome[Seq[SearchResult]]
      
      // todo: add tests on the JSON data
    }
    
    // todo: test alternate / failure conditions
  }
}
