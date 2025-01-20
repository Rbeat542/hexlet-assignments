package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    protected TagInterface tag;
    public static String label;

    public LabelTag(String label, TagInterface tag) {
        this.tag = tag;
        this.label = label;
    }

    @Override
    public String render() {
        String newTag = tag.render();
        String str = "<label>" + label + newTag + "</label>";
        return str;
    }
}
// END
