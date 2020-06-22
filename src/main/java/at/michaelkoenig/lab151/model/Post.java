package at.michaelkoenig.lab151.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Michael König
 */
@Entity(name="post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(name="post_message")
    @Basic
    @NotBlank
    private String message;

    @ManyToOne
    @JoinColumn(name = "post_user", referencedColumnName = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}