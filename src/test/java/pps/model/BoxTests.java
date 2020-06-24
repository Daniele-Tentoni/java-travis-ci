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

    @Test
    public void testTake() {
        assertFalse(this.box.isTaken());
        this.box.take("daniele");
        assertTrue(this.box.isTaken());
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
}
