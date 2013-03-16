package akkastudy.simpleactor.scala

import org.scalatest.FlatSpec
import akka.actor.{Props, ActorSystem}
import akkastudy.loggingactor.scala.SimpleActorScala

class SimpleActorTest extends FlatSpec {
  behavior of "A simple actor"

  it should "receive our message in Scala" in {
    val system = ActorSystem("MySystem")
    val myActor = system.actorOf(Props[SimpleActorScala], name = "simpleActorJava")
    myActor ! "Simple Test"
    myActor ! "test"
  }

  it should "be at location akka://MySystem/user/simpleActorJava" in {
    val system = ActorSystem("MySystem")
    system.actorOf(Props[SimpleActorScala], name = "simpleActorJava")
    val myActor = system.actorFor("akka://MySystem/user/simpleActorJava")
    myActor ! "Simple Test"
  }

  it should "throw send a dead letter if the actor is not found" in {
    val system = ActorSystem("MySystem")
    system.actorOf(Props[SimpleActorScala], name = "simpleActorJava")
    val myActor = system.actorFor("akka://MySystem/user/somethingElseIShouldn\'tBeLookingFor")
    myActor ! ("Simple Test")
  }
}