package fjnu.framework.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PrintMyActorRefActor extends AbstractActor {

  public static Props prop() {
    return Props.create(PrintMyActorRefActor.class, PrintMyActorRefActor::new);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .matchEquals("printit", p -> {
          ActorRef actorRef = getContext().actorOf(Props.empty(), "second-actor");
          System.out.println("second:" + actorRef);
        })
        .build();
  }

  public static void main(String[] args) throws Exception {
    ActorSystem actorSystem = ActorSystem.create("test");
    ActorRef first = actorSystem.actorOf(PrintMyActorRefActor.prop(), "first-actor");
    System.out.println("system:" + actorSystem);
    System.out.println("first:" + first);
    first.tell("printit", ActorRef.noSender());

    try {
      System.in.read();
    } finally {
      actorSystem.terminate();
    }
  }
}


