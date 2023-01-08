package eulife.dao.implementation;

import eulife.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDAO() {
    }

    public List<User> userList() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }
}
