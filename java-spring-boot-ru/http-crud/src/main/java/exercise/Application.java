package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "4") Integer pageCount, @RequestParam(defaultValue = "2") Integer limit){
        if (limit > 0) {
            var pagesTotal = Math.ceil (posts.size() / limit);
            Integer startIndex = (pageCount-1) * limit;
            Integer endIndex = pageCount * limit;
            return posts.subList(startIndex, endIndex);
        }
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return post;
    }

    @PostMapping("/posts")
    public Post createPost(@RequestParam Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post put(@PathVariable String id, @RequestBody Post newPost) {
        var postFound = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (postFound.isPresent()) {
            Post post = postFound.get();
            post.setBody(newPost.getBody());
            post.setTitle(newPost.getTitle());
        }
        return newPost;
    }

    @PostMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
    // END
}
