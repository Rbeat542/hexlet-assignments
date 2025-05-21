package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post getPost(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return post;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        postRepository.save(post);
        return postRepository.findAll().getLast();
    }

    @PutMapping(path = "/{id}")
    public Post editPost(@PathVariable Long id, @RequestBody Post post) {
        var postFound = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id = " + id + " not found!"));
        postFound.setTitle(post.getTitle());
        postFound.setBody(post.getBody());
        return post;
    }

    @DeleteMapping(path = "/{id}")
    public void erase(@PathVariable Long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}


// END
