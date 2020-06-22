package at.michaelkoenig.lab151.repository;

import at.michaelkoenig.lab151.model.Post;
import at.michaelkoenig.lab151.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Michael König
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUser(User user);

    void deleteAllByUser(User user);
}
