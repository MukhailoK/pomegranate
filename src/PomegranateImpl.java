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
        List<Box> boxes = generateBoxes();
        long totalSeedsInAllBoxes = getTotalSeedsInAllBoxes(boxes);
        int maxSeedsInOneBox = getMaximumSeedsInOneBox(boxes);

        System.out.println("Check if boxes is correctly:");
        boolean totalSeeds = totalSeedsInAllBoxes >= 400_000
                && totalSeedsInAllBoxes <= 2_800_000;
        System.out.println("count seeds in all boxes: " + totalSeeds);
        boolean countBoxes = boxes.size() >= 100 && boxes.size() <= 200;
        System.out.println("count boxes: " + countBoxes);
        boolean countGranates = boxes.stream()
                .allMatch(p -> p.getGranates().size() >= 10 && p.getGranates().size() <= 20);
        System.out.println("count granates: " + countGranates);
        boolean countSeeds = boxes.stream()
                .allMatch(p -> p.getGranates().stream()
                        .allMatch(s -> s.getSeeds().size() >= 400 && s.getSeeds().size() <= 700));
        System.out.println("count seeds: " + countSeeds);
        System.out.println("=======================");
        System.out.println("Total seeds in all boxes = " + totalSeedsInAllBoxes);
        System.out.println("Max seeds in box = " + maxSeedsInOneBox);
        List<String> boxesWithMaximumSeeds = findBoxesWithMaxSeeds(boxes, maxSeedsInOneBox);
        System.out.println("Names of boxes with max seeds:");
        boxesWithMaximumSeeds.forEach(System.out::println);
    }

    public static List<Box> generateBoxes() {
        Random random = new Random();

        return IntStream.range(0, random.nextInt(101) + 100)
                .mapToObj(i -> {
                    Box box = new Box();
                    box.setName("Box #" + i);

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