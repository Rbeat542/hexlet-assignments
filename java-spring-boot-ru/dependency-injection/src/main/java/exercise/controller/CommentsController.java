package exercise.controller;

//import exercise.model.Post;
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

import exercise.model.Comment;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Comment getComment(@PathVariable Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id = " + id + " not found!"));
        return comment;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return commentRepository.findAll().getLast();
    }

    @PutMapping(path = "/{id}")
    public Comment editComment(@PathVariable Long id, @RequestBody Comment comment) {
        var commentFound = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id = " + id + " not found!"));
        commentFound.setBody(comment.getBody());
        return commentFound;
    }

    @DeleteMapping(path = "/{id}")
    public void erase(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}


// END
