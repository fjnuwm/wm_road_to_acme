package fjnu.javamiddle.wangwenjun.chapter02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;

    private final String destination;

    private final List<String> fightList = new ArrayList<>();

    public FightQueryTask (String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            fightList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight:%s query successful\n", getName());
        } catch (Exception e) {

        }
    }

    @Override
    public List<String> get() {
        return this.fightList;
    }
}
