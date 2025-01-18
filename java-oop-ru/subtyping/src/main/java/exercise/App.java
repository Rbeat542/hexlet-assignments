package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage entry) {
        HashMap<String, String> tempMap = new HashMap<>();
        Map<String, String> entryMap = entry.toMap();
        var entries = entryMap.entrySet();
        for (var keyset : entries) {
            tempMap.put(keyset.getValue(), keyset.getKey());
        }
        entryMap.forEach((k, v) -> entry.unset(k));
        tempMap.forEach((k, v) -> entry.set(k, v));
        //entry = new InMemoryKV(tempMap);
    }
}
// END
