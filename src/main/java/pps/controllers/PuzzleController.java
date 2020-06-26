package pps.controllers;

import org.springframework.web.bind.annotation.*;
import pps.model.Box;
import pps.model.ReturnMessage;
import pps.stores.PuzzleStore;

import java.util.Set;

@RestController
public class PuzzleController {
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

    @PutMapping("/release/{player}/{id}")
    public boolean release(@PathVariable("player") final String player, @PathVariable("id") final int id) {
        PuzzleStore.instance().release(player, id);
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

    @GetMapping("/state")
    @ResponseBody
    public String getGameState() {
        if(PuzzleStore.instance().isFinished()) {
            return "Finished";
        }

        if(PuzzleStore.instance().isGenerated()) {
            return "Generated";
        }

        return "Nothing";
    }
}
