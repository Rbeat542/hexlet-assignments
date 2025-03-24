package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void post(Context ctx) {
        var id = ctx.pathParamAsClass("{id}", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() ->new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {
        var queryString = ctx.queryString();
        var pagesTotal = Math.ceil(PostRepository.getEntities().size() / 5);
        if (queryString == null) {
            var posts = PostRepository.getEntities().subList(0, 5);
            var page = new PostsPage(posts);
            var pagePrev = 0;
            var pageNext = 2;
            if (pageNext <= pagesTotal)  {
                pageNext = pageNext;
            } else {
                pageNext = 0;
            }
            ctx.render("posts/index.jte", model("page", page, "pageNext", pageNext, "pagePrev", pagePrev));
        } else {
            var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var posts = PostRepository.findAll(pageNumber, 5);
            var page = new PostsPage(posts);
            var pageNext = pageNumber + 1;
            if (pageNext <= pagesTotal)  {
                pageNext = pageNext;
            } else {
                pageNext = 0;
            }
            var pagePrev = pageNumber - 1;
            if (pagePrev > 0)  {
                pagePrev = pagePrev;
            } else {
                pagePrev = 0;
            }
            ctx.render("posts/index.jte", model("page", page, "pageNext", pageNext, "pagePrev", pagePrev));
        }
    }


    // END
}
