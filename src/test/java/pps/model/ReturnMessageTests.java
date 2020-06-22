package pps.model;

import org.junit.Assert;
import org.junit.Test;

public class ReturnMessageTests {
    @Test public void testGetResult(){
        ReturnMessage message = new ReturnMessage(true, "Nothing");
        Assert.assertTrue(message.getResult());

        ReturnMessage failure = new ReturnMessage(false, "Error");
        Assert.assertFalse(failure.getResult());
    }

    @Test public void testGetMessage() {
        ReturnMessage result = new ReturnMessage(true, "Result");
        Assert.assertEquals("Result", result.getMessage());
    }
}
