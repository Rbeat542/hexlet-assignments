package exercise;

import java.util.Map;
import java.util.List;  

// BEGIN
public class PairedTag extends Tag {
    public String tag;
    public Map<String, String> map;
    public String line;
    public List<Tag> list;

    public PairedTag(String tag, Map<String, String> map, String line, List<Tag> list) {
        this.tag = tag;
        this.map = map;
        this.line = line;
        this.list = list;
    }

    @Override
    public String toString() {
        String start = super.startTag();
        String end = super.endTag();
        return start + inner1() + inner2() + end;
    }

    private String inner1() {
        String str = "";
        str = str + tag;
        var entries = map.entrySet();
        for (var entry:entries) {
            str = str + " " + entry.getKey() + "=\"" + entry.getValue() + "\"";
        }
        str += ">";
        return str;
    }

    private String inner2() {
        String str = "";
        str = str + line;
        for (var entry:list) {
            str = str + entry.toString();
        }
        str = str + "</" + tag;
        return str;
    }


}
// END
