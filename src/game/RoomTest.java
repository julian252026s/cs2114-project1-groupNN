package game;

import student.TestCase;

public class RoomTest
    extends TestCase
{
    private Room room;
    private Room secondRoom;
    private Puzzle puzzle;
    private Item item;

    public void setUp()
    {
        puzzle =
            new Puzzle(
                "What is 2 + 2?",
                "4",
                "Add two and two");

        room =
            new Room(
                "Room 1",
                "First room",
                puzzle);

        secondRoom =
            new Room(
                "Room 2",
                "Second room");

        item =
            new Item(
                "Sword",
                "A sword");
    }

    public void testGetName()
    {
        assertEquals(
            "Room 1",
            room.getName());
    }

    public void testGetDescription()
    {
        assertEquals(
            "First room",
            room.getDescription());
    }

    public void testSetAndGetExit()
    {
        room.setExit(
            "FORWARD",
            secondRoom);

        assertEquals(
            secondRoom,
            room.getExit("FORWARD"));

        assertNull(
            room.getExit("LEFT"));
    }

    public void testInvalidExit()
    {
        room.setExit(
            "JUMP",
            secondRoom);

        assertNull(
            room.getExit("JUMP"));
    }

    public void testRemoveItem()
    {
        room.addItem(item);

        assertEquals(
            "[Sword]",
            room.getItems());

        assertEquals(
            item,
            room.removeItem("Sword"));

        assertEquals(
            "[]",
            room.getItems());

        assertNull(
            room.removeItem("Sword"));
    }

    public void testHasPuzzle()
    {
        assertTrue(
            room.hasPuzzle());

        puzzle.checkAnswer("4");

        assertFalse(
            room.hasPuzzle());
    }

    public void testGetPuzzle()
    {
        assertEquals(
            puzzle,
            room.getPuzzle());

        assertNull(
            secondRoom.getPuzzle());
    }
}