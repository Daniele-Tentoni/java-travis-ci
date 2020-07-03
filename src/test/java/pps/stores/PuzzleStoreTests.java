package pps.stores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pps.model.Box;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleStoreTests {
    @BeforeEach public void beforeEach() {

    }

    @Test
    @DisplayName("Test basic instance")
    public void testInstance() {
        PuzzleStore store = PuzzleStore.instance();
        assertNotNull(store);
    }

    @Test public void testIsGenerated() {
        assertTrue(PuzzleStore.instance().isGenerated());
    }

    @Test public void testIsFinished() {
        assertFalse(PuzzleStore.instance().isFinished());
    }

    @Test public void testGetBoxes() {
        Set<Box> boxes = PuzzleStore.instance().getBoxes();
        assertFalse(boxes.isEmpty());
    }

    @Test public void testTake() {
        PuzzleStore.instance().take("daniele", 0);
        Optional<Box> box = PuzzleStore.instance().getBoxes()
                .stream().filter(f -> f.getOriginalPosition() == 0).findFirst();
        assertTrue(box.isPresent());
        assertTrue(box.get().isTaken());
        assertTrue(box.get().isTaken("daniele"));
    }

    @Test public void testTakeAlreadyTaken() {
        PuzzleStore.instance().take("daniele", 0);
        PuzzleStore.instance().take("stefano", 0);
        Optional<Box> box = PuzzleStore.instance().getBoxes()
                .stream().filter(f -> f.getOriginalPosition() == 0).findFirst();
        assertTrue(box.isPresent());
        assertTrue(box.get().isTaken());
        assertFalse(box.get().isTaken("stefano"));
    }

    @Test public void testRelease() {
        PuzzleStore.instance().take("daniele", 1);
        Optional<Box> box = PuzzleStore.instance().getBoxes()
                .stream().filter(f -> f.getOriginalPosition() == 1).findFirst();
        assertTrue(box.isPresent());
        assertTrue(box.get().isTaken());

        PuzzleStore.instance().release("daniele", 1);
        box = PuzzleStore.instance().getBoxes()
                .stream().filter(f -> f.getOriginalPosition() == 1).findFirst();
        assertTrue(box.isPresent());
        assertFalse(box.get().isTaken());
    }

    @Test public void testMove() {
        Box b0 = getBox(0);
        Box b1 = getBox(1);
        int p0 = b0.getCurrentPosition();
        int p1 = b1.getCurrentPosition();
        assertNotEquals(p0, p1);
        assertFalse(b0.isTaken());
        assertFalse(b0.isTaken("daniele"));
        assertFalse(b1.isTaken());
        assertFalse(b1.isTaken("daniele"));
        PuzzleStore.instance().take("daniele", 0);
        PuzzleStore.instance().take("daniele", 1);
        assertTrue(b0.isTaken());
        assertTrue(b0.isTaken("daniele"));
        assertTrue(b1.isTaken());
        assertTrue(b1.isTaken("daniele"));
        PuzzleStore.instance().move("daniele", 0, 1);

        // After move.
        assertFalse(b0.isTaken());
        assertNotEquals("daniele", b0.getTaker());
        assertNotEquals(p0, p1);
        assertEquals(p0, b1.getCurrentPosition());
        assertEquals(p1, b0.getCurrentPosition());
    }

    @Test public void testMoveAndNoMoreTaken() {
        // Testa se dopo un movimento ci siano delle caselle che risultano ancora prese dal giocatore.
        PuzzleStore.instance().take("daniele", 0);
        PuzzleStore.instance().move("daniele", 0, 1);
        PuzzleStore.instance().take("daniele", 3);
        PuzzleStore.instance().move("daniele", 3, 6);

        long count = PuzzleStore.instance().getBoxes().stream()
                .filter(f -> f.isTaken("daniele")).count();
        assertEquals(0, count);
    }

    private Box getBox(int index) {
        return PuzzleStore.instance().getBoxes()
                .stream().filter(f -> f.getOriginalPosition() == index).findFirst().get();
    }
}
