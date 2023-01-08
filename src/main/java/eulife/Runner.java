package eulife;

import eulife.dao.implementation.UserDAO;
import eulife.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
//        UserDAO userDAO = context.getBean(UserDAO.class);
//        System.out.println(userDAO.userList());

        User user = new User.UserBuilder()
                .setAge(18)
                .setFirst_name("Myroslava")
                .build();

        System.out.println(user.getFirst_name());
        System.out.println(user.getAge());
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://database-3.cbrtuyqxuzpi.eu-central-1.rds.amazonaws.com:3306/test");
        dataSource.setUsername("admin");
        dataSource.setPassword("adminadmin");
        return new JdbcTemplate(dataSource);
    }


}
