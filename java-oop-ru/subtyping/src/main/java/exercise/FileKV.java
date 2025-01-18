package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    public String path;
    public Map<String, String> map;

    public FileKV(String path, Map<String, String> map) {
        this.path = path;
        this.map = map;
        Utils.writeFile(path, Utils.serialize(this.map));
    }

    public void set(String key, String value) {
        Map<String, String> tempMap = Utils.deserialize(Utils.readFile(path));
        tempMap.put(key, value);
        Utils.writeFile(path, Utils.serialize(tempMap));
    }

    public void unset(String key) {
        Map<String, String> tempMap = Utils.deserialize(Utils.readFile(path));
        tempMap.remove(key);
        Utils.writeFile(path, Utils.serialize(tempMap));
    }

    public String get(String key, String defaultValue) {
        Map<String, String> tempMap = Utils.deserialize(Utils.readFile(path));
        if (tempMap.containsKey(key)) {
            return tempMap.get(key);
        } else {
            return defaultValue;
        }
    }

    public Map<String, String> toMap() {
        Map<String, String> tempMap = Utils.deserialize(Utils.readFile(path));
        return new HashMap<String, String>(tempMap);
    }
}
// END
