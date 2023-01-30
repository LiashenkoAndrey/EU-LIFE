package eulife.domain;

import jakarta.persistence.Embeddable;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Role implements GrantedAuthority {

    public Role() {
    }
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static List<Role> defaultRole() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        return roles;
    }
}
