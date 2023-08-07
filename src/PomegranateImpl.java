import model.Box;
import model.Pomegranate;
import model.Seed;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PomegranateImpl {
    //min total = 400_000
    //max total = 2_800_000

    public static void main(String[] args) {
        List<Box> boxMap = generateBoxes();
        System.out.println(getTotalSeedsInAllBoxes(boxMap) >= 400_000
                && getTotalSeedsInAllBoxes(boxMap) <= 2_800_000);
        System.out.println(getTotalSeedsInAllBoxes(boxMap));
        int maxSeedsInOneBox = getMaximumSeedsInOneBox(boxMap);
        System.out.println(maxSeedsInOneBox);
        List<String> boxesWithMaximumSeeds = findBoxesWithMaxSeeds(boxMap, maxSeedsInOneBox);
        boxesWithMaximumSeeds.forEach(System.out::println);
    }

    public static List<Box> generateBoxes() {
        Random random = new Random();

        return  IntStream.range(0, random.nextInt(101) + 100)
                .mapToObj(i -> {
                    Box box = new Box();
                    box.setName("Box" + i);

                    List<Pomegranate> pomegranates = IntStream.range(0, random.nextInt(11) + 10)
                            .mapToObj(j -> {
                                Pomegranate pomegranate = new Pomegranate();
                                List<Seed> seeds = IntStream.range(0, random.nextInt(301) + 400)
                                        .mapToObj(k -> new Seed())
                                        .collect(Collectors.toList());
                                pomegranate.setSeeds(seeds);
                                return pomegranate;
                            })
                            .collect(Collectors.toList());

                    box.setGranates(pomegranates);
                    return box;
                })
                .collect(Collectors.toList());
    }

    public static long getTotalSeedsInAllBoxes(List<Box> boxes) {
        return boxes.stream()
                .flatMap(box -> box.getGranates().stream())
                .mapToInt(pomegranate -> pomegranate.getSeeds().size())
                .sum();
    }

    public static int getMaximumSeedsInOneBox(List<Box> boxes) {
        return boxes.stream()
                .flatMap(box -> box.getGranates().stream())
                .mapToInt(pomegranate -> pomegranate.getSeeds().size())
                .max()
                .orElse(0);
    }

    public static List<String> findBoxesWithMaxSeeds(List<Box> boxes, int maxSeeds) {
        return boxes.stream()
                .filter(box -> box.getGranates().stream().anyMatch(pomegranate -> pomegranate.getSeeds().size() == maxSeeds))
                .map(Box::getName)
                .collect(Collectors.toList());
    }
}
