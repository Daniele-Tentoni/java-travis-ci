package pps.stores;

import pps.model.Box;

import java.util.*;
import java.util.stream.IntStream;

public class PuzzleStore {
    private static PuzzleStore singleton;

    public static PuzzleStore instance() {
        if (singleton == null)
            singleton = new PuzzleStore();
        return singleton;
    }

    private final Set<Box> tiles;
    private boolean finished;
    private final int rows, columns;

    private PuzzleStore() {
        this.tiles = new TreeSet<>();
        this.rows = 3;
        this.columns = 5;
        try {
            generatePuzzle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void take(String player, int id) {
        tiles.stream().filter(f -> f.getId() == id).findFirst().ifPresent(p -> {
            if (!p.isTaken())
                p.take(player);
        });
    }

    public void move(String player, int first, int second) {
        tiles.stream().filter(f -> f.getId() == first).findFirst().ifPresent(p -> tiles.stream().filter(g -> g.getId() == second).findFirst().ifPresent(q ->{
            int pos = p.getCurrentPosition();
            p.setCurrentPosition(player, q.getCurrentPosition());
            q.setCurrentPosition(player, pos);
        }));
        checkSolution();
    }

    public void freeTiles(String name) {
        tiles.stream().takeWhile(t -> t.isTaken(name)).forEach(e -> e.take(""));
    }

    public Set<Box> getBoxes() {
        return tiles;
    }

    public boolean isGenerated() {
        return !tiles.isEmpty();
    }

    public boolean isFinished() {
        return finished;
    }

    public void generatePuzzle() {
        int position = 0;

        final List<Integer> randomPositions = new ArrayList<>();
        IntStream.range(0, rows*columns).forEach(randomPositions::add);
        Collections.shuffle(randomPositions);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tiles.add(new Box( i * columns + j, position, randomPositions.get(position)));
                position++;
            }
        }
    }

    private void checkSolution() {
        if(tiles.stream().allMatch(Box::isInRightPlace)) {
            this.finished = true;
        }
    }
}