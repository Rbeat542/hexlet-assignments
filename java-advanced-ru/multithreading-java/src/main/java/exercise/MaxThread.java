package exercise;

import java.util.Arrays;
import java.util.Map;

// BEGIN
public class MaxThread extends Thread {
    public static int[] arr;
    public static Map<String, Integer> map;

public MaxThread(int[]arr, Map<String, Integer> map) {
    this.arr = arr;
    this.map = map;
}

    @Override
    public void run() {
        System.out.println("INFO: Thread Max started");
        int max = Arrays.stream(arr).max().orElse(0);
        System.out.println("INFO: Thread Max finished");
        map.put("max", max);
        //System.out.println("LOG: digit is " + max);

    }
}
// END
