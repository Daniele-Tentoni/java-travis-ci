package pps;

import pps.model.Box;

import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleStore {
    private static PuzzleStore singleton;

    public static PuzzleStore instance() {
        if (singleton == null)
            singleton = new PuzzleStore();
        return singleton;
    }

    final Set<Box> cells;
    final int rows, columns;

    private PuzzleStore() {
        this.cells = new HashSet<>();
        this.rows = 3;
        this.columns = 5;
        generatePuzzle();
    }

    public void take(String player, int id) {
        cells.stream().filter(f -> f.getId() == id).findFirst().ifPresent(p -> {
            if (!p.isTaken())
                p.take(player);
        });
    }

    public void move(String player, int id, int x, int y) {
        cells.stream().filter(f -> f.getId() == id).findFirst().ifPresent(p -> p.move(player, x, y));
    }

    public Set<Box> getBoxes() {
        return cells;
    }

    public boolean isGenerated() {
        return !cells.isEmpty();
    }

    public void generatePuzzle() {
        final List<Integer> randomPositions = new ArrayList<>();
        IntStream.range(0, rows * columns).forEach(randomPositions::add);
        Collections.shuffle(randomPositions);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells.add(new Box(i * columns + columns, rows, columns));
            }
        }
    }
}