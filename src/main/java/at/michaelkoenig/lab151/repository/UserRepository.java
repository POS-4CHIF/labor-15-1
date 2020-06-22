package at.michaelkoenig.lab151.repository;

import at.michaelkoenig.lab151.model.Post;
import at.michaelkoenig.lab151.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Michael König
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
