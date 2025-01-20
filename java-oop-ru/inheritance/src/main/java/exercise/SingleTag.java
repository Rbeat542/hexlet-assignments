package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public String tag;
    public Map<String, String> map;

    public SingleTag(String tag, Map<String, String> map) {
        this.tag = tag;
        this.map = map;
    }

    @Override
    public String toString() {
        String start = super.startTag();
        String end = super.endTag();
        return start + inner() + end;
    }

    private String inner() {
        String str = "";
        str += tag;
        var entries = map.entrySet();
        for (var entry : entries) {
            str = str + " " + entry.getKey() + "=\"" + entry.getValue() + "\"";
        }
        return str;
    }
}
// END
