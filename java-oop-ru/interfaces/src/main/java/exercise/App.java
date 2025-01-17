package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> list, Integer numberOfLines) {
        List<String> newlist = new ArrayList<>();
        newlist = list.stream()
                .sorted(Comparator.comparing(Home :: getArea))
                .map(s -> s.toString())
                .toList();
        return numberOfLines < list.size() ? newlist.subList(0, numberOfLines) : newlist;
    }
}
// END
