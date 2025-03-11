package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            //var users = Data.getUsers().subList(0,4);
            var queryString =ctx.queryString();
            if (queryString == null) {
                var users = Data.getUsers().subList(0,5);
                ctx.json(users);
            } else {
                var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
                var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
                var startIndex = (page - 1) * per;
                var endIndex = page * per;
                var usersExact = Data.getUsers().subList(startIndex, endIndex);
                ctx.json(usersExact);
            }
        });
        return app;
        // END

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
