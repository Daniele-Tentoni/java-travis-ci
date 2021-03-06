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

    private Set<Box> tiles;
    private boolean finished;
    private int rows, columns;

    private PuzzleStore() {
        reset();
    }

    public void take(String player, int id) {
        tiles.stream().filter(f -> f.getOriginalPosition() == id).findFirst().ifPresent(p -> {
            if (!p.isTaken())
                p.take(player);
        });
    }

    public void release(String player, int id) {
        tiles.stream().filter(f -> f.getOriginalPosition() == id).findFirst().ifPresent(p -> {
            if (p.isTaken(player))
                p.take("");
        });
    }

    public void move(String player, int first, int second) {
        tiles.stream()
                .filter(f -> f.getOriginalPosition() == first
                        && f.isTaken(player))
                .findFirst()
                .ifPresent(p ->
                        tiles.stream()
                                .filter(g -> g.getOriginalPosition() == second
                                        && g.isTaken())
                                .findFirst()
                                .ifPresent(q -> {
                                    int pos = p.getCurrentPosition();
                                    p.setCurrentPosition(player, q.getCurrentPosition());
                                    q.setCurrentPosition(player, pos);
                                }));
        finished = checkSolution();
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
        IntStream.range(0, rows * columns).forEach(randomPositions::add);
        Collections.shuffle(randomPositions);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tiles.add(new Box(position, randomPositions.get(position)));
                position++;
            }
        }
    }

    public void reset() {
        this.tiles = new TreeSet<>();
        this.rows = 3;
        this.columns = 5;
        this.finished = false;
        generatePuzzle();
    }

    private boolean checkSolution() {
        return tiles.stream().allMatch(Box::isInRightPlace);
    }
}