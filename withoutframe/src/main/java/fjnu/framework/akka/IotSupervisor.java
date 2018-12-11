package fjnu.framework.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class IotSupervisor extends AbstractActor {
  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

  public static Props props() {
    return Props.create(IotSupervisor.class, IotSupervisor::new);
  }

  @Override
  public void preStart() throws Exception {
    log.info("Iot Application started");
  }

  @Override
  public void postStop() throws Exception {
    log.info("Iot Application stopped");
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .build();
  }

  public static void main(String[] args) throws Exception {
    ActorSystem actorSystem = ActorSystem.create("iot-system");

    ActorRef actorRef = actorSystem.actorOf(IotSupervisor.props(), "iot-supervisor");
    System.in.read();
    actorSystem.terminate();
  }
}
