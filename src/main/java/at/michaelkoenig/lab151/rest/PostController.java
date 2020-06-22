package at.michaelkoenig.lab151.rest;

import at.michaelkoenig.lab151.exception.RestException;
import at.michaelkoenig.lab151.model.Post;
import at.michaelkoenig.lab151.model.User;
import at.michaelkoenig.lab151.repository.PostRepository;
import at.michaelkoenig.lab151.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Michael König
 */
@RestController
public class PostController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsByUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RestException(String.format("User %d does not exist", id), HttpStatus.NOT_FOUND);
        }

        return postRepository.findAllByUser(user.get());
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> addPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RestException(String.format("User %d does not exist", id), HttpStatus.NOT_FOUND);
        }

        post = postRepository.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/posts/{id}").
                build(post.getId());
        return ResponseEntity.created(uri).body(post);
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()) {
            throw new RestException(String.format("Post %d does not exist", id), HttpStatus.NOT_FOUND);
        }

        return post.get();
    }

}
