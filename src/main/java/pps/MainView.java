package pps;

import com.fasterxml.jackson.core.util.VersionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@SpringBootApplication
@RestController
public class MainView {
    private static final int APP_VERSION = 2;

    @GetMapping("/players")
    public Set<String> getPlayers() {
        return PlayerStore.instance().getPlayers();
    }

    @GetMapping("/players/{studentName}")
    public boolean addPlayer(@PathVariable("studentName") final String studentName){
        PlayerStore.instance().addPlayer(studentName);
        return true;
    }

    @GetMapping("/info")
    public String getInfo() {
        return "I'm a Spring Boot base microservice. I'm running " + getVersion();
    }

    /**
     * JavaDoc comment for Say Hello function.
     */
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Hello World!");
        return "Hello, my world!";
    }

    @GetMapping("/version")
    public int getVersion() {
        return APP_VERSION;
    }

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(MainView.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }
}
