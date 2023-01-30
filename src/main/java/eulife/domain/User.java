package eulife.domain;



import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    private String email;
    private String login;
    private String password;

    private Date date_of_creation;

    private boolean not_locked;

    @OneToOne(cascade = CascadeType.ALL)
    private eulife.domain.UserDetails user_details;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> role = new ArrayList<>();

    public boolean isNot_locked() {
        return not_locked;
    }

    public void setNot_locked(boolean not_locked) {
        this.not_locked = not_locked;
    }

    public eulife.domain.UserDetails getUser_details() {
        return user_details;
    }

    public void setUser_details(eulife.domain.UserDetails user_details) {
        this.user_details = user_details;
    }


    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return not_locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date_of_creation=" + date_of_creation +
                ", not_locked=" + not_locked +
                ", user_details=" + user_details +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return not_locked == user.not_locked && first_name.equals(user.first_name) && last_name.equals(user.last_name) && Objects.equals(email, user.email) && login.equals(user.login) && password.equals(user.password) && date_of_creation.equals(user.date_of_creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, email, login, password, date_of_creation, not_locked);
    }
}
