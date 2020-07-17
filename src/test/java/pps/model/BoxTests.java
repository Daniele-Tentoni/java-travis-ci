package pps.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A special test case")
public class BoxTests {
    Box box;

    @BeforeEach
    public void before() {
        this.box = new Box(0, 0);
    }

    @Test public void testSetCurrentPositionNotTaken() {
        assertEquals(0, box.getCurrentPosition());
        box.setCurrentPosition("daniele", 1);
        assertEquals(0, box.getCurrentPosition());
    }

    @Test public void testSetCurrentPositionTaken() {
        assertEquals(0, box.getCurrentPosition());
        box.take("daniele");
        box.setCurrentPosition("stefano", 1);
        assertNotEquals(1, box.getCurrentPosition());
        box.setCurrentPosition("daniele", 1);
        assertEquals(1, box.getCurrentPosition());
    }

    @Test
    public void testTake() {
        assertFalse(box.isTaken());
        assertFalse(box.isTaken("daniele"));
        box.take("daniele");
        assertTrue(box.isTaken());
        assertTrue(box.isTaken("daniele"));
        assertFalse(box.isTaken("stefano"));
        assertEquals("daniele", box.getTaker());
    }

    @Test public void testMove() {
        assertEquals(0, this.box.getCurrentPosition());
        this.box.take("daniele");
        this.box.setCurrentPosition("daniele", 1);
        assertEquals(1, this.box.getCurrentPosition());
    }

    @Test public void testIsInRightPlace() {
        assertTrue(this.box.isInRightPlace());
    }

    @Test public void testToString() {
        String msg = "Box{" +
                "taken=" + false +
                ", originalPosition=" + 0 +
                ", currentPosition=" + 0 +
                '}';
        assertEquals("", this.box.toString());
    }
}
