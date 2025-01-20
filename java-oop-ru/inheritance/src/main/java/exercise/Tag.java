package exercise;

import java.util.Map;

// BEGIN
public class Tag {
    public String tag;
    public Map<String, String> map;

    public Tag(String tag, Map<String, String> map) {
        this.tag = tag;
        this.map = map;
    }

    public String format() {
        String line = "";
        var entries = map.entrySet();
        for (var entry : entries) {
            line = line + String.format(" %s=\"%s\"", entry.getKey(), entry.getValue());
        }
        return line;
    }

    public String toString() {
        return "<" + tag + format() + ">";
    }

    public String getTag() {
        return tag;
    }
}
// END
