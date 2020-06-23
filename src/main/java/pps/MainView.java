package pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;
import pps.model.Box;

import java.util.Collections;
import java.util.Set;

@SpringBootApplication
@RestController
public class MainView extends SpringBootServletInitializer {
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

    @PutMapping("/take/{player}/{id}")
    @ResponseBody
    public boolean take(@PathVariable("player") String player, @PathVariable("id") int id) {
        PuzzleStore.instance().take(player, id);
        return true;
    }

    @PutMapping("/move/{player}/{first}/{second}")
    @ResponseBody
    public boolean move(@PathVariable("player") String player, @PathVariable("first") int first, @PathVariable("second") int second) {
        PuzzleStore.instance().move(player, first, second);
        return true;
    }

    @GetMapping("/mapping")
    @ResponseBody
    public Set<Box> getMapping() {
        return PuzzleStore.instance().getBoxes();
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
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }
}