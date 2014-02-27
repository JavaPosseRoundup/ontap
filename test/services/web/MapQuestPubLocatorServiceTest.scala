package services.web

import org.scalatest.{Matchers, FunSpec}
import java.net.URL

/**
 * Created by dick on 2/26/14.
 */
class MapQuestPubLocatorServiceTest extends FunSpec with Matchers {

  describe ("The mapquest service companion object") {
    describe ("the serviceURLFor method") {
      it ("should return a correctly formatted url for given params") {
        val theUrl = MapQuestPubLocatorService.serviceURLFor(38.868702F, -106.98394F, 20, 10)

        theUrl should be ("""http://www.mapquestapi.com/search/v2/radius?key=Fmjtd%7Cluur210znl%2C8x%3Do5-90ysqa&maxMatches=10&origin=38.868702,-106.98394&hostedData=mqap.ntpois%7Cgroup_sic_code=?%7C581208&radius=20.0&units=k""")
      }

      ignore ("should create a valid URL") {  // to save our daily limit
        val theUrl = MapQuestPubLocatorService.serviceURLFor(38.868702F, -106.98394F, 20, 10)

        val asUrl = new URL(theUrl)

        val asString = scala.io.Source.fromURL(asUrl).getLines.mkString

        asString should not be (empty)
      }
    }
  }
}
