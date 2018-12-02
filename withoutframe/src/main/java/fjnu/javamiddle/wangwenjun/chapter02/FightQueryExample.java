package fjnu.javamiddle.wangwenjun.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> result = search("SH", "FJ");
        System.out.println("=======result=======");
        result.forEach(System.out::println);
    }

    public static List<String> search(String origin, String destination) {
        final List<String> result = new ArrayList<>();

        List<FightQueryTask> fightQueryTasks = fightCompany.stream()
                .map(f -> createQueryTask(f, origin, destination))
                .collect(Collectors.toList());

        fightQueryTasks.forEach(FightQueryTask::start);

        fightQueryTasks.forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {

            }
        });

        fightQueryTasks.stream().map(FightQueryTask::get).collect(Collectors.toList()).forEach(result::addAll);
        return result;
    }

    public static FightQueryTask createQueryTask(String fightCompany, String origin, String destination) {
        return new FightQueryTask(fightCompany, origin, destination);
    }
}
