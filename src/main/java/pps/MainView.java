package pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MainView {

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