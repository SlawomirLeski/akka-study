package akkastudy.eventbus.scala

import akka.actor.{ActorSystem, Props}
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  * @author Daniel Hinojosa
  * @since 4/9/14 9:36 PM
  *        url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
  *        email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
  *        tel: 505.363.5832
  */
class SimpleActorEventBusTest extends FlatSpec with Matchers {
  behavior of "an ActorEventBus"

  it should """work exclusively with an ActorEventBus""" in {

    val actorSystem = ActorSystem("SimpleActorSystem")
    val simpleActorEventBus = new SimpleActorEventBus()
    val simpleActorListenerRef = actorSystem.actorOf(Props[SimpleActorListener], "simpleActorListener")
    val simpleActorRef = actorSystem.actorOf(Props[SimpleActor], "simpleActor")

    simpleActorEventBus.subscribe(simpleActorListenerRef, "Hello?")
    simpleActorEventBus.publish(SimpleActorRefEvent("Category-A", "Category-A message", "2012-01-12 20:11:03", simpleActorRef))
  }
}