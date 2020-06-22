package pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@RestController
public class MainView {
    @GetMapping("/info")
    public String info() {
        return "I'm a Spring Boot base microservice.";
    }

    /**
     * JavaDoc comment for Say Hello function.
     */
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Hello World!");
        return "Hello, my world!";
    }

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(MainView.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }
}
