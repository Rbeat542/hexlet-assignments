package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sun.net.httpserver.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private static ArrayList<Post> staticList;

    @GetMapping("/users/{id}/posts")
    public List<Post> index(@PathVariable int id) {
        var workingList = staticList.stream()
                    .filter(p -> p.getUserId() == id)
                    .toList();
        return workingList;
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable int id, @RequestBody Post post) {
            if (staticList == null) {
                staticList = new ArrayList<>(Data.getPosts());
            }
            staticList.add(post);
            staticList.getLast().setUserId(id);
        return staticList.getLast();
    }
}

// END
