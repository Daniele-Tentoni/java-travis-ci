package pps.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReturnMessageTests {
    @Test
    public void testGetResult(){
        ReturnMessage message = new ReturnMessage(true, "Nothing");
        assertTrue(message.getResult());

        ReturnMessage failure = new ReturnMessage(false, "Error");
        assertFalse(failure.getResult());
    }

    @Test public void testGetMessage() {
        ReturnMessage result = new ReturnMessage(true, "Result");
        assertEquals("Result", result.getMessage());
    }

    @Test public void getMessage() {
        ReturnMessage msg = new ReturnMessage(true, "Result");
        assertEquals("Result", msg.getMessage());
        String newMsg = "Good result";
        msg.setMessage(newMsg);
        assertEquals(newMsg, msg.getMessage());
    }
}
