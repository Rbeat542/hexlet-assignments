package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void index(Context ctx) {
        var page = new PostsPage(PostRepository.getEntities());
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("posts/index.jte", model("page", page));
    }


    public static void create(Context ctx) {
        var nameEntered = ctx.formParamAsClass("name", String.class).get();
        var bodyEntered = ctx.formParamAsClass("body", String.class).get();
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(res -> res.length() >= 2, "Name is too short")
                    .get();
            var body = ctx.formParamAsClass("body", String.class).get();
            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.sessionAttribute("flash", "Post was successfully created!");
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var page = new BuildPostPage(nameEntered, bodyEntered, e.getErrors());
            ctx.sessionAttribute("flash", "Post has not been created!");
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("posts/build.jte", model("page", page));
        }
    }

    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
