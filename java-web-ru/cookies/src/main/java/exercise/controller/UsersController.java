package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {


    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) throws Exception {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + "not found"));
        var userToken = user.getToken();
        if (!userToken.equals(ctx.cookieMap().get("mytoken"))) {
          ctx.redirect(NamedRoutes.buildUserPath());
        };
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws Exception {
        var token = Security.generateToken();
        ctx.cookie("mytoken", token);
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        var id = UserRepository.getEntities().getLast().getId();
        ctx.redirect(NamedRoutes.userPath(id));
    }
    // END
}

