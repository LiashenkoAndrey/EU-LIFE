package eulife.domain;



import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    private int age;
    private String email;
    private String login;
    private String password;

    private Date date_of_creation;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(UserBuilder builder) {
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.age = builder.age;
        this.email = builder.email;
        this.login = builder.login;
        this.password = builder.password;
        this.date_of_creation = builder.date_of_creation;
        this.role = builder.role;
    }

    public static class UserBuilder {

        public UserBuilder() {
        }

        private Long id;
        private String first_name;
        private String last_name;
        private int age;
        private String email;
        private String login;
        private String password;
        private Date date_of_creation;

        private Roles role;



        public UserBuilder setFirst_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public UserBuilder setLast_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setDate_of_creation(Date date_of_creation) {
            this.date_of_creation = date_of_creation;
            return this;
        }

        public UserBuilder setRole(Roles role) {
            this.role = role;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
