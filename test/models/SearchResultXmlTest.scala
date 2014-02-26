package models

import org.scalatest._
import scala.xml.Node
import models.xmlutil.XmlFormat

/**
 * Created by dick on 2/26/14.
 */
class SearchResultXmlTest extends FunSpec with Matchers {

  val singleLocation: Node = <location>
    <id>2406</id>
    <name>Two Bells</name>
    <status>Beer Bar</status>
    <reviewlink>http://beermapping.com/maps/reviews/reviews.php?locid=2406</reviewlink>
    <proxylink>http://beermapping.com/maps/proxymaps.php?locid=2406&amp;d=5</proxylink>
    <blogmap>http://beermapping.com/maps/blogproxy.php?locid=2406&amp;d=1&amp;type=norm</blogmap>
    <street>2313 4th Ave</street>
    <city>Seattle</city>
    <state>WA</state>
    <zip>98121</zip>
    <country>United States</country>
    <phone>(206) 441-3050</phone>
    <overall>84.99995</overall>
    <imagecount>4</imagecount>
  </location>


  val allLocations = <bmp_locations>
    <location>
      <id>2406</id>
      <name>Two Bells</name>
      <status>Beer Bar</status>
      <reviewlink>http://beermapping.com/maps/reviews/reviews.php?locid=2406</reviewlink>
      <proxylink>http://beermapping.com/maps/proxymaps.php?locid=2406&amp;d=5</proxylink>
      <blogmap>http://beermapping.com/maps/blogproxy.php?locid=2406&amp;d=1&amp;type=norm</blogmap>
      <street>2313 4th Ave</street>
      <city>Seattle</city>
      <state>WA</state>
      <zip>98121</zip>
      <country>United States</country>
      <phone>(206) 441-3050</phone>
      <overall>84.99995</overall>
      <imagecount>4</imagecount>
    </location>
    <location>
      <id>9262</id>
      <name>Ring O'Bells Brewery</name>
      <status>Brewery</status>
      <reviewlink>http://beermapping.com/maps/reviews/reviews.php?locid=9262</reviewlink>
      <proxylink>http://beermapping.com/maps/proxymaps.php?locid=9262&amp;d=5</proxylink>
      <blogmap>http://beermapping.com/maps/blogproxy.php?locid=9262&amp;d=1&amp;type=norm</blogmap>
      <street>Pennygillam Way</street>
      <city>Launceston</city>
      <state>Cornwall</state>
      <zip>PL15 7ED</zip>
      <country>United Kingdom</country>
      <phone>01566 777787</phone>
      <overall>0</overall>
      <imagecount>0</imagecount>
    </location>
  </bmp_locations>

  describe("Xml reader for SearchResults") {
    it ("should read a single search results node into the case class") {
      import XmlFormat._

      val theLoc = fromXml[SearchResult](singleLocation)
      theLoc should be (
        SearchResult("2406", "Two Bells", "Beer Bar", "2313 4th Ave",
                     "Seattle", "WA", "98121", "United States")
      )
    }

    it ("should read a sequence of search results into seq of case classes") {
      import XmlFormat._
      val locs = fromXmlSeq[SearchResult](allLocations \\ "location")
      locs should be (
        Seq(
          SearchResult("2406", "Two Bells", "Beer Bar", "2313 4th Ave",
            "Seattle", "WA", "98121", "United States"),
          SearchResult("9262", "Ring O'Bells Brewery", "Brewery", "Pennygillam Way",
            "Launceston", "Cornwall", "PL15 7ED", "United Kingdom")
        )
      )
    }
  }

}
