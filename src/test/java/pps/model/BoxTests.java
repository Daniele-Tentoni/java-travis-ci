package pps.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoxTests {
    Box box;

    @Before public void before() {
        this.box = new Box(0, 0, 0);
    }

    @Test public void testTake() {
        Assert.assertFalse(this.box.isTaken());
        this.box.take("daniele");
        Assert.assertTrue(this.box.isTaken());
    }

    @Test public void testMove() {
        Assert.assertEquals(0, this.box.getCurrentPosition());
        this.box.take("daniele");
        this.box.setCurrentPosition("daniele", 1);
        Assert.assertEquals(1, this.box.getCurrentPosition());
    }

    @Test public void testIsInRightPlace() {
        Assert.assertTrue(this.box.isInRightPlace());
    }
}
