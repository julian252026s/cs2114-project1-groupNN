package game;

import student.TestCase;

public class InputHandlerTest extends TestCase
{
    private InputHandler handler;

    public void setUp() {
        handler = new InputHandler();
    }

    public void testValidateCommand() {
        assertNull(handler.validateCommand(null));
        assertNull(handler.validateCommand(""));
        assertNull(handler.validateCommand("   "));
        assertNull(handler.validateCommand("FLY"));
        assertEquals("FORWARD", handler.validateCommand("forward"));
        assertEquals("LEFT", handler.validateCommand("  left  "));
        assertEquals("HELP", handler.validateCommand("HeLp"));
        assertEquals("TAKE", handler.validateCommand("TAKE sword"));
    }

    public void testIsValidCommand() {
        assertTrue(handler.isValidCommand("RIGHT"));
        assertTrue(handler.isValidCommand("QUIT"));
        assertFalse(handler.isValidCommand("go"));
        assertFalse(handler.isValidCommand("FLY"));
        assertFalse(handler.isValidCommand(null));
    }
}
