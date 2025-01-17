package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    CharSequence str;

    ReversedSequence(String str) {
        String reversedString = "";
        int iMax = str.length();
        for (int i = 0; i < iMax; i++) {
            reversedString = str.charAt(i) + reversedString;
        }
        this.str = reversedString;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public String toString() {
        return str.toString();
    }

    @Override
    public char charAt(int index) {
        //String reversedString = str.toString();
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int finish) {
        String reversedString = str.toString();
        return reversedString.substring(start, finish);
    }
}
// END
