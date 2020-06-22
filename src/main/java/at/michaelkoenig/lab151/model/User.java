package at.michaelkoenig.lab151.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Michael König
 */
@Entity(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", unique = true)
    @Size(min = 3)
    private String name;

    @Column(name = "user_birthday")
    @PastOrPresent
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Post> getUsers() {
        return users;
    }

    public void setUsers(List<Post> users) {
        this.users = users;
    }
}