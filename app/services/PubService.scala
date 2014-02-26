package services

import models.{Pub, PubSeq}
import scala.concurrent.Future
import play.api.{Logger, Play}
import play.api.libs.json.Json
import play.api.Play.current
import com.github.davidmoten.geo.GeoHash

trait PubService {
  
  def near(lat: Float, lng: Float, scale: Int): Future[PubSeq]

}

class FakePubService extends PubService {
  
  def allFakePubs = Play.resourceAsStream("ontap_mapquest_response.json").map { stream =>
    val fileString = scala.io.Source.fromInputStream(stream).getLines().mkString("\n")
    PubSeq((Json.parse(fileString) \\ "fields").map { json =>
      json.as[Pub]
    })
  } getOrElse PubSeq(Seq())
  
  override def near(lat: Float, lng: Float, scale: Int): Future[PubSeq] = {
    val geoHash = GeoHash.encodeHash(lat, lng, scale)
    
    val matchedPubs = PubSeq(allFakePubs.pubs.filter { pub =>
      val subGeoHash = pub.geoHash.substring(0, scale)
      subGeoHash == geoHash
    })
    
    Future.successful(matchedPubs)
  }
}