package models

import org.scalatest._

/**
 * Basic demo of GeoHash
 */
class GeoHashTest extends FunSpec with Matchers {

  describe("GeoHash for latitude/longitude default length") {
    it ("should convert a single lat/lon to a String geohash at default length") {
      import com.github.davidmoten.geo.GeoHash

      val theLoc = GeoHash.encodeHash(45.0,-95.0)
      val expected = "9zurypzpgxcz"
      theLoc should be (expected)
    }
  }

  describe("GeoHash for latitude/longitude at length 7 (+/- 70m)") {
    it ("should convert a single lat/lon to a String geohash at length 7 (+/- 70m)") {
      import com.github.davidmoten.geo.GeoHash

      val theLoc = GeoHash.encodeHash(45.0,-95.0, 7)
      val expected = "9zurypz"
      theLoc should be (expected)
    }
  }
}
