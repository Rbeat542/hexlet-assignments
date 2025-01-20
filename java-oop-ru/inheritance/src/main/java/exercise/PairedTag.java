package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {
    public String text;
    public List<Tag> list;

    public PairedTag(String tag, Map<String, String> map, String text, List<Tag> list) {
        super(tag, map);
        this.text = text;
        this.list = list;
    }

    @Override
    public String toString() {
        String line = "<" + tag + super.format() + ">" + text;
        for (var entry : list) {
            line = line + "<" + entry.getTag() + entry.format() + ">";
        }
        line = line + "</" + tag + ">";
        return line;
    }
}
// END
