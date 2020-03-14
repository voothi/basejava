package ru.voothi.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStreamApi {
    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0))
                .get(integers.stream().mapToInt(Integer::intValue).sum() % 2 != 0);
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    public static void main(String[] args) {
        int arr1[] = {1, 2, 3, 3, 2, 3};
        int arr2[] = {9, 8};
        System.out.println("Stream: 1)");
        System.out.println(minValue(arr1));
        System.out.println(minValue(arr2));

        System.out.println("Stream: 2)");
        List<Integer> integers = new ArrayList<>();
//        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        System.out.println(oddOrEven(integers));
    }

}
