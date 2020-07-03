package pps.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pps.stores.PlayerStore;
import pps.stores.PuzzleStore;

/**
 * Represent the state of the server.
 */
@RestController
public class ServerController {
    /**
     * Reset the current context to start a new game.
     */
    @DeleteMapping("/server/reset")
    public boolean serverReset() {
        try {
            PlayerStore.instance().reset();
            PuzzleStore.instance().reset();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Get some useful info about the current context.
     * @return Infos in a string.
     */
    @GetMapping("/info")
    @ResponseBody
    public String getInfo() {
        return "I'm a Spring Boot base microservice. I'm running " + getVersion();
    }

    /**
     * Sau hello to the user.
     * @return Hello !!
     */
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello, my world!";
    }

    private static final int APP_VERSION = 4;

    /**
     * Get the version of the application.
     * @return The Number Vesion.
     */
    @GetMapping("/version")
    @ResponseBody
    public int getVersion() {
        return APP_VERSION;
    }
}