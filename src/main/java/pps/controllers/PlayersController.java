package pps.controllers;

import org.springframework.web.bind.annotation.*;
import pps.stores.PlayerStore;
import pps.model.ReturnMessage;
import pps.stores.PuzzleStore;

import java.util.Set;

/**
 * Controller for Players entity.
 */
@RestController
public class PlayersController {
    /**
     * Get all players of the current instance.
     * @return A set with all players.
     */
    @GetMapping("/players")
    @ResponseBody
    public Set<String> getPlayers() {
        return PlayerStore.instance().getPlayers();
    }

    /**
     * Add a new player to the existing set of player in the current context.
     * @param name The name of the player.
     * @return A Return Message with the result of this operation.
     */
    @PostMapping("/players")
    @ResponseBody
    public ReturnMessage addPlayer(@RequestBody final String name) {
        try {
            String tmp = name.replace("\"", "");
            tmp = tmp.replace("\\", "");
            PlayerStore.instance().addPlayer(tmp);
            return new ReturnMessage(true, "Player added");
        } catch (Exception e) {
            return new ReturnMessage(false, e.getMessage());
        }
    }

    /**
     * Delete a player from the current context.
     * @param name The name of the player.
     * @return A Return Message with the result of this operation.
     */
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
