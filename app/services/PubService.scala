package services

import models.{Pub, PubSeq}
import scala.concurrent.Future
import play.api.Play
import play.api.libs.json.Json
import play.api.Play.current

trait PubService {
  
  def near(lat: Float, lng: Float): Future[PubSeq]

}

class FakePubService extends PubService {
  override def near(lat: Float, lng: Float): Future[PubSeq] = {
    val pubSeq = Play.resourceAsStream("ontap_mapquest_response.json").map { stream =>
      val fileString = scala.io.Source.fromInputStream(stream).getLines().mkString("\n")
      PubSeq((Json.parse(fileString) \\ "fields").map { json =>
        json.as[Pub]
      })
    } getOrElse PubSeq(Seq())
    Future.successful(pubSeq)
  }
}