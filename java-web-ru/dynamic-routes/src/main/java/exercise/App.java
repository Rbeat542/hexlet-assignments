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

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
