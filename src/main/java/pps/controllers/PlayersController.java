package pps.controllers;

import org.springframework.web.bind.annotation.*;
import pps.stores.PlayerStore;
import pps.model.ReturnMessage;
import pps.stores.PuzzleStore;

import java.util.Set;

@RestController
public class PlayersController {
    @GetMapping("/players")
    @ResponseBody
    public Set<String> getPlayers() {
        return PlayerStore.instance().getPlayers();
    }

    @PostMapping("/players")
    @ResponseBody
    public ReturnMessage addPlayer(@RequestBody final String studentName) {
        try {
            String tmp = studentName.replace("\"", "");
            tmp = tmp.replace("\\", "");
            PlayerStore.instance().addPlayer(tmp);
            return new ReturnMessage(true, "Player added");
        } catch (Exception e) {
            return new ReturnMessage(false, e.getMessage());
        }
    }

    @DeleteMapping("/players/{name}")
    @ResponseBody
    public ReturnMessage deletePlayer(@PathVariable("name") final String name) {
        try {
            PlayerStore.instance().deletePlayer(name);
            PuzzleStore.instance().freeTiles(name);
            return new ReturnMessage(true, "Player removed");
        } catch (Exception e) {
            return new ReturnMessage(false, e.getMessage());
        }
    }
}
