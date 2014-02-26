package models.xmlutil

import scala.xml.Node
import scala.annotation.implicitNotFound

/**
 * Created by dick on 2/26/14.
 */
@implicitNotFound(msg = "No XML formatter available for ${T}, try adding an implicit XmlFormat implementation")
trait XmlFormat[T] {
  def readXml(node: Node): T
  def writeXml(item: T): Node
}
