package pps;

import pps.model.Box;

import java.util.HashSet;
import java.util.Set;

public class PuzzleStore {
    private static PuzzleStore singleton;

    public static PuzzleStore instance() {
        if (singleton == null)
            singleton = new PuzzleStore();
        return singleton;
    }

    private final Set<Box> cells;

    private PuzzleStore() {
        this.cells = new HashSet<>();
        cells.add(new Box(0, 0, 0));
        cells.add(new Box(1, 1, 0));
        cells.add(new Box(2, 0, 1));
        cells.add(new Box(3, 1, 1));
    }

    public void take(String player, int id) {
        cells.stream().filter(f -> f.getId() == id).findFirst().ifPresent(p -> {
            if(!p.isTaken())
                p.take(player);
        });
    }

    public void move(String player, int id, int x, int y) {
        cells.stream().filter(f -> f.getId() == id).findFirst().ifPresent(p -> p.move(player, x, y));
    }

    public Set<Box> getBoxes() {
        return cells;
    }
}
