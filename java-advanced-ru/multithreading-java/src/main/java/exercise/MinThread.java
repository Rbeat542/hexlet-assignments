package exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class MinThread extends Thread {
    public static int[]arr;
    public static Map<String, Integer> map;

    public MinThread(int[]arr, Map<String, Integer> map) {
        this.arr = arr;
        this.map = map;
    }
    @Override
    public void run() {

        System.out.println("INFO: Thread Min started");
        int min = Arrays.stream(arr).min().orElse(0);
        System.out.println("INFO: Thread Min finished");
        map.put("min", min);
        //System.out.println("LOG: digit is " + min);
    }
}
// END
