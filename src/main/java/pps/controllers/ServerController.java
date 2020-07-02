package pps.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import pps.stores.PlayerStore;
import pps.stores.PuzzleStore;

@RestController
public class ServerController {
    @DeleteMapping("server/reset")
    public void serverReset() {
        PlayerStore.instance().reset();
        PuzzleStore.instance().reset();
    }
}
