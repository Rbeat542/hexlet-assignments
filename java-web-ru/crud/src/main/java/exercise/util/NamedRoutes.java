package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String indexPath() {
        return "/posts";
    }

    /*public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }
*/
    public static String postPath() {
        return "/posts";
    }
    // END
}
