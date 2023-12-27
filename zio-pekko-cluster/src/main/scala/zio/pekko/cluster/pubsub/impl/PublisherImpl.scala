package zio.pekko.cluster.pubsub.impl

import zio.{ Task, ZIO }
import zio.pekko.cluster.pubsub.{ MessageEnvelope, Publisher }

import org.apache.pekko.actor._
import org.apache.pekko.cluster.pubsub.DistributedPubSubMediator.Publish

private[pubsub] trait PublisherImpl[A] extends Publisher[A] {
  val getMediator: ActorRef

  override def publish(topic: String, data: A, sendOneMessageToEachGroup: Boolean = false): Task[Unit] =
    ZIO.attempt(getMediator ! Publish(topic, MessageEnvelope(data), sendOneMessageToEachGroup))
}
