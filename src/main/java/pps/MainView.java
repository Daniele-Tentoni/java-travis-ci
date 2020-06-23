package pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import pps.model.Box;

import java.util.Set;

@SpringBootApplication
@RestController
public class MainView {
    private static final int APP_VERSION = 3;

    @GetMapping("/info")
    @ResponseBody
    public String getInfo() {
        return "I'm a Spring Boot base microservice. I'm running " + getVersion();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        System.out.println("Hello World!");
        return "Hello, my world!";
    }

    @GetMapping("/version")
    @ResponseBody
    public int getVersion() {
        return APP_VERSION;
    }

    /*
    @GetMapping(
        value = "/get-image",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getPuzzleImage() {
        return PuzzleStore.instance()
    }*/

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(MainView.class);
        // app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }
}