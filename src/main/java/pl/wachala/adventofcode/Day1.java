package pl.wachala.adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day1 {

    public static void main(String[] args) {
        String[] split = getTestInput().split("\n");
        ArrayList<Long> left = new ArrayList<>(split.length);
        ArrayList<Long> right = new ArrayList<>(split.length);

        for (String s : split) {
            String[] row = s.split("\s+");

            left.add(Long.parseLong(row[0]));
            right.add(Long.parseLong(row[1]));
        }

        System.out.println(totalDistance(left, right));
        System.out.println(similarityScore(left, right));
    }

    //Part I
    private static long totalDistance(ArrayList<Long> left, ArrayList<Long> right) {
        Collections.sort(left);
        Collections.sort(right);

        long totalDistance = 0L;
        for (int i = 0; i < left.size(); i++) {
            long l = left.get(i);
            long r = right.get(i);

            totalDistance += Math.abs(l - r);
        }

        return totalDistance;
    }

    //Part II
    private static long similarityScore(ArrayList<Long> left, ArrayList<Long> right) {
        Map<Long, Integer> rightOccurrences = new HashMap<>();
        right.forEach(
                x -> rightOccurrences.put(x, rightOccurrences.getOrDefault(x, 0) + 1)
        );

        return left.stream()
                .map(x -> x * rightOccurrences.getOrDefault(x, 0))
                .mapToLong(x -> x)
                .sum();
    }

    public static String getTestInput() {
        return """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3""";
    }

}
