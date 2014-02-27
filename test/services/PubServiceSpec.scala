package services

import org.scalatest.{Matchers, FunSpec}
import scala.concurrent.{Await, Future}
import models.Beer
import scala.concurrent.duration._

class PubServiceSpec extends FunSpec with Matchers {

  describe("PubService") {
    describe("beers") {
      it("should return empty list if pub has none") {
        val pubService = new FakePubService
        val beers: Future[Seq[Beer]] = pubService.beers("Coors")

        val beerList: Seq[Beer] = Await.result(beers, 10 milliseconds)

        beerList should be (empty)
      }
    }
  }
}
