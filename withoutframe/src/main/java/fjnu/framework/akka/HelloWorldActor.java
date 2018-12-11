package fjnu.framework.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorldActor extends UntypedActor {
  public HelloWorldActor(int num) {
    System.out.println(num);
  }

  @Override
  public void onReceive(Object o) throws Throwable {
    System.out.println("hello world" + o.toString());
  }

  public static void main(String[] args) {
    ActorSystem actorSystem = ActorSystem.create("hellowworld");
    ActorRef actorRef = actorSystem.actorOf(Props.create(HelloWorldActor.class, 20), "helloworld");
    actorRef.tell("baga", null);
  }
}
