package pps.controllers;

import org.springframework.web.bind.annotation.*;
import pps.stores.PuzzleStore;
import pps.model.Box;

import java.util.Set;

@RestController
public class PuzzleController {
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
}
