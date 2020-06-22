package at.michaelkoenig.lab151.rest;

import at.michaelkoenig.lab151.exception.RestException;
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
public class UserController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        if (user.getId() == null)
            throw new RestException(String.format("User %i must not have an id", user.getId()), HttpStatus.BAD_REQUEST);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/users/{id}").
                build(user.getId());
        return ResponseEntity.created(uri).body(userRepository.save(user));
    }

    @DeleteMapping("/users/{id}")
    public User removeUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RestException(String.format("User %i does not exist", id), HttpStatus.NOT_FOUND);
        }

        postRepository.deleteAllByUser(user.get());
        userRepository.delete(user.get());

        return user.get();
    }

}
