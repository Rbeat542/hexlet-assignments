package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN

@RestController
@RequestMapping("/posts")
public class PostsController {

    /*@Autowired
    private CommentDTO commentDTO;

    @Autowired
    private PostDTO postDTO;
*/
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index () {
        var results = postRepository.findAll();
        var posts = results.stream().map(this::toDTO)
                .toList();
        return posts;
    }

    @GetMapping(path = "/{id}")
    public PostDTO index (@PathVariable Long id){
        var result = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return toDTO(result);
    }

    private PostDTO toDTO(Post post) {
        var postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        Long id = post.getId();
        List<Comment> list = commentRepository.findByPostId(id);
        postDTO.setComments(commentToDTO(list));
        return postDTO;
    }

    private List<CommentDTO> commentToDTO(List<Comment> comments) {
        List<CommentDTO> list = new ArrayList<CommentDTO>();
        for (var element:comments) {
            var commentDTO = new CommentDTO();
            commentDTO.setId(element.getId());
            commentDTO.setBody(element.getBody());
            list.add(commentDTO);
        }
        //postDTO.setComments(commentRepository.findByPostId(post.getId()));
        return list;
    }
}
// END
