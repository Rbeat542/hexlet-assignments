package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    public HashMap<String, String> map;

    public InMemoryKV(Map<String, String> map) {
        this.map = new HashMap<>(map);
    }

    public void set(String key, String value) {
        map.put(key, value);
    }

    public void unset(String key) {
        map.remove(key);
    }

    public String get(String key, String defaultValue) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return defaultValue;
        }
    }

    public Map<String, String> toMap() {
        return new HashMap<String, String>(map);
    }
}
// END
