package services

import models.{Beer, Pub, PubSeq}
import scala.concurrent.Future
import play.api.{Logger, Play}
import play.api.libs.json.Json
import play.api.Play.current
import com.github.davidmoten.geo.GeoHash

trait PubLocatorService {
  def near(lat: Float, lng: Float, maybeBeerId: Option[String], scale: Int): Future[PubSeq]
}

trait BeerListingService {
  def beers(pubId: String): Future[Seq[Beer]]
}

class FakePubService extends PubLocatorService with BeerListingService {
  
  def allFakePubs = Play.resourceAsStream("ontap_mapquest_response.json").map { stream =>
    val fileString = scala.io.Source.fromInputStream(stream).getLines().mkString("\n")
    PubSeq((Json.parse(fileString) \\ "fields").map { json =>
      json.as[Pub]
    })
  } getOrElse PubSeq(Seq())
  
  override def near(lat: Float, lng: Float, maybeBeerId: Option[String],
                    scale: Int): Future[PubSeq] = {
    val geoHash = GeoHash.encodeHash(lat, lng, scale)
    
    val localPubs = allFakePubs.pubs.filter { pub =>
      val subGeoHash = pub.geoHash.substring(0, scale)
      subGeoHash == geoHash
    }
    
    val pubs = maybeBeerId match {
      case Some(beerId) =>
//        localPubs.filter { pub =>
//
//          pub.beers.contains(beerId)
//        }
        localPubs
      case None =>
        localPubs
    }
    
    Future.successful(PubSeq(pubs))
  }

  override def beers(pubId: String): Future[Seq[Beer]] = Future.successful(Seq.empty)
}
