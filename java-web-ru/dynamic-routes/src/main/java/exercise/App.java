package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });


    /*    app.get("/users", ctx -> {
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
        return app;*/

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });
//begin
        app.get("/companies/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            if (COMPANIES.size() >= id) {
                for (var companie:COMPANIES) {
                    if (Integer.valueOf(companie.get("id")) ==  id) {
                        ctx.json(companie);
                        break;
                    }
                }
            } else {
                throw new NotFoundResponse("Company not found"); }
            });
//end

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    /*public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        // Позже мы разберем эти конструкции подробнее
        var user = UserRepository.find(id) // Ищем пользователя в базе по id
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
    }
    */
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
