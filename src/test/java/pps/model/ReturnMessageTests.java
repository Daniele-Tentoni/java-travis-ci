package pps.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReturnMessageTests {
    String res = "Result";

    @Test
    public void testGetResult(){
        ReturnMessage message = new ReturnMessage(true, "Nothing");
        assertTrue(message.getResult());

        ReturnMessage failure = new ReturnMessage(false, "Error");
        assertFalse(failure.getResult());
    }

    @Test public void testGetMessage() {
        ReturnMessage result = new ReturnMessage(true, res);
        assertEquals(res, result.getMessage());

        ReturnMessage another = new ReturnMessage(true, "Another result");
        assertEquals("Another result", another.getMessage());
    }

    @Test public void getMessage() {
        ReturnMessage msg = new ReturnMessage(true, res);
        assertEquals(res, msg.getMessage());

        String newMsg = "Good result";
        msg.setMessage(newMsg);
        assertEquals(newMsg, msg.getMessage());
    }
}
