package exercise;

// BEGIN
public class Tag {
    public final String startTag;
    public final String endTag;


    public Tag() {
        startTag = "<";
        endTag = ">";
    }

    public String startTag() {
        return startTag;
    }

    public String endTag() {
        return endTag;
    }
}
// END
