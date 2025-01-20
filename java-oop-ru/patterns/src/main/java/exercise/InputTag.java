package exercise;

// BEGIN
public class InputTag implements TagInterface {
    public static String type;
    public static String value;

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        StringBuilder str = new StringBuilder()
                .append("<input type=\"")
                .append(type)
                .append("\" value=\"")
                .append(value)
                .append("\">");
        return str.toString();
    }
}
// END
