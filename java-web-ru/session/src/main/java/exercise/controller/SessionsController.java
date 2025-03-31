package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import java.util.Collection;


public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage("", "");
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) throws Exception {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");
        var usersList = UsersRepository.search(name);
        var users = usersList.stream()
                .filter(element -> element.getName().equals(name))
                .toList();
        var encryptedPass = encrypt(password);
        if (users.isEmpty() || !users.getFirst().getPassword().equals(encryptedPass)) {
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        } else {
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect(NamedRoutes.rootPath());
        }

    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void root(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }

    // END
}
