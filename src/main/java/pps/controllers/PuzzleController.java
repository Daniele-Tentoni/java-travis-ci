package pps.controllers;

import org.springframework.web.bind.annotation.*;
import pps.model.Box;
import pps.model.ReturnMessage;
import pps.stores.PuzzleStore;

import java.util.Optional;
import java.util.Set;

/**
 * Represent all action to do with a Puzzle.
 */
@RestController
public class PuzzleController {
    /**
     * A player wanna take a tile and mark that with his name.
     *
     * @param player Name of the taker.
     * @param id     Id of the taken.
     * @return The Result Message with the result of this operation.
     */
    @PutMapping("/take/{player}/{id}")
    @ResponseBody
    public ReturnMessage take(@PathVariable("player") final String player, @PathVariable("id") final int id) {
        try {
            PuzzleStore.instance().take(player, id);
            return new ReturnMessage(true, String.format("Player %s taken %s tile", player, id));
        } catch (Exception e) {
            return new ReturnMessage(false, e.getMessage());
        }
    }

    /**
     * A player wanna release a tile and unmark that with his name.
     *
     * @param player Name of the player.
     * @param id     Id of the taken.
     * @return The Result Message with the result of this operation.
     */
    @PutMapping("/release/{player}/{id}")
    @ResponseBody
    public ReturnMessage release(@PathVariable("player") final String player, @PathVariable("id") final int id) {
        try {
            PuzzleStore.instance().release(player, id);
            return new ReturnMessage(true, String.format("Player %s released %s tile", player, id));
        } catch (Exception e) {
            return new ReturnMessage(false, e.getMessage());
        }
    }

    /**
     * A player wanna move a tile and swap with another.
     *
     * @param player A player that wanna swap tiles.
     * @param first  First tile to move.
     * @param second Second tile to move.
     * @return The Result Message of this operation.
     */
    @PutMapping("/move/{player}/{first}/{second}")
    @ResponseBody
    public ReturnMessage move(@PathVariable("player") String player, @PathVariable("first") int first, @PathVariable("second") int second) {
        try {
            PuzzleStore.instance().move(player, first, second);
            Box f = PuzzleStore.instance().getBoxes().stream().filter(e -> e.getOriginalPosition() == first).findFirst().get();
            Box s = PuzzleStore.instance().getBoxes().stream().filter(e -> e.getOriginalPosition() == second).findFirst().get();
            return new ReturnMessage(true, "Boxes:" + f.toString() + "/" + s.toString());
        } catch (Exception ex) {
            return new ReturnMessage(false, ex.getMessage());
        }
    }

    /**
     * A player wanna know the state of a tile.
     *
     * @param player Player caller.
     * @param id     Id of the requested resource.
     * @return The Return Message of this operation.
     */
    @GetMapping("/state/{player}/{id}")
    @ResponseBody
    public ReturnMessage getState(@PathVariable("player") final String player, @PathVariable("id") final int id) {
        try {
            Optional<Box> box = PuzzleStore.instance().getBoxes().stream()
                    .filter(f -> f.getOriginalPosition() == id)
                    .findFirst();
            return box
                    .map(value -> new ReturnMessage(true, String.valueOf(value.isTaken(player))))
                    .orElseGet(() -> new ReturnMessage(false, "Tile not found"));
        } catch (Exception e) {
            return new ReturnMessage(false, e.getMessage());
        }
    }

    /**
     * A player wanna know the mapping of the current context.
     *
     * @return The Set of all tiles.
     */
    @GetMapping("/mapping")
    @ResponseBody
    public Set<Box> getMapping() {
        return PuzzleStore.instance().getBoxes();
    }

    /**
     * A player wanna know the state of the game.
     *
     * @return The State of the game in the current context.
     */
    @GetMapping("/state")
    @ResponseBody
    public String getGameState() {
        if (PuzzleStore.instance().isFinished()) {
            return "Finished";
        }

        if (PuzzleStore.instance().isGenerated()) {
            return "Generated";
        }

        return "Nothing";
    }
}
