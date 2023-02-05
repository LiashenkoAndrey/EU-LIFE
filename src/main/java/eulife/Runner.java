package eulife;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Bean
    public MongoDatabase connect() {
        MongoClient db = new MongoClient("localhost", 27017);
        MongoDatabase database = db.getDatabase("helloWorld");
        return database;
    }
}
