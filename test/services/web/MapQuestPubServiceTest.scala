package services.web

import org.scalatest.{Matchers, FunSpec}

/**
 * Created by dick on 2/26/14.
 */
class MapQuestPubServiceTest extends FunSpec with Matchers {

  describe ("The mapquest service companion object") {
    describe ("the serviceURLFor method") {
      it ("should return a correctly formatted url for given params") {
        val theUrl = MapQuestPubService.serviceURLFor(38.868702F, -106.98394F, 20, 10)

        theUrl should be ("""http://www.mapquestapi.com/search/v2/radius?key=Fmjtd%7Cluur210znl%2C8x%3Do5-90ysqa&maxMatches=10&origin=38.868702,-106.98394&hostedData=mqap.ntpois|group_sic_code=?|581208&radius=20&units=k""")
      }
    }
  }
}
