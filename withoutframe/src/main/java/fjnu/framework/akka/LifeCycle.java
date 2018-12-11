package fjnu.framework.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class LifeCycle {
  public static void main(String[] args) {
    ActorSystem actorSystem = ActorSystem.create("root");
    ActorRef actorRef = actorSystem.actorOf(ActorOne.prop(), "actor1");
    actorRef.tell("stop", ActorRef.noSender());
  }
}

class ActorOne extends AbstractActor {

  public static Props prop() {
    return Props.create(ActorOne.class, ActorOne::new);
  }

  @Override
  public void preStart() throws Exception {
    System.out.println("Actor1 start");
    ActorRef actorRef = getContext().actorOf(Props.create(ActorTow.class, ActorTow::new));
  }

  @Override
  public void postStop() throws Exception {
    System.out.println("Actor1 stop");
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .matchEquals("stop", p -> {
          getContext().stop(getSelf());
        })
        .build();
  }
}

class ActorTow extends AbstractActor {

  @Override
  public void preStart() throws Exception {
    System.out.println("Actor2 start");
  }

  @Override
  public void postStop() throws Exception {
    System.out.println("Actor2 stop");
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .build();
  }
}