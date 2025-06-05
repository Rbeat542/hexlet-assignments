package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        LOGGER.info("INFO: Starting main thread");
        var map = new HashMap<String, Integer>();
        try {
            var minThread = new MinThread(arr, map);
            minThread.start();
            minThread.join();
            var maxThread = new MaxThread(arr, map);
            maxThread.start();
            maxThread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("INFO: Finishing main thread");
        return map;
    }
    // END
}
