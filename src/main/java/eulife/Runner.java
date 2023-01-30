package eulife;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner {

    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
