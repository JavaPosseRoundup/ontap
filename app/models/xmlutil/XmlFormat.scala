package models.xmlutil

import scala.xml.{NodeSeq, Node}
import scala.annotation.implicitNotFound

/**
 * Created by dick on 2/26/14.
 */
@implicitNotFound(msg = "No XML formatter available for ${T}, try adding an implicit XmlFormat implementation")
trait XmlFormat[T] {
  def readXml(node: Node): T
  def writeXml(item: T): Node
}

object XmlFormat {
  def toXml[T: XmlFormat](item: T): Node = implicitly[XmlFormat[T]].writeXml(item)
  def fromXml[T: XmlFormat](xml: Node): T = implicitly[XmlFormat[T]].readXml(xml)

  def toXmlSeq[T: XmlFormat](items: Seq[T]): NodeSeq = {
    val formatter = implicitly[XmlFormat[T]]
    val itemsAsXml: NodeSeq = for {
      item <- items
    } yield formatter.writeXml(item)

    itemsAsXml
  }

  def fromXmlSeq[T: XmlFormat](nodes: NodeSeq): Seq[T] = {
    val formatter = implicitly[XmlFormat[T]]
    val items = (for {
      node <- nodes
    } yield formatter.readXml(node)).toVector
    items
  }
}