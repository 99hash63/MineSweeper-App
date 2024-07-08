package com.mineSweeper.app;

import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputHandlerTest {

    @Test
    public void testGetIntInput_ValidInput() {
        Scanner scanner = new Scanner("5");
        int result = InputHandler.getIntInput(scanner);
        assertEquals(5, result);
    }

    @Test
    public void testGetIntInput_NonIntegerInput() {
        Scanner scanner = new Scanner("abc\nfff\nd\n2");
        int result = InputHandler.getIntInput(scanner);
        assertEquals(2, result);
    }

//    @Test
//    public void testIsSquareWithinValidRange_ValidInput() {
//        boolean result1 = InputHandler.isSquareWithinValidRange("A1", 5);
//        assertTrue(result1);
//
//        boolean result2 = InputHandler.isSquareWithinValidRange("E5", 5);
//        assertTrue(result2);
//    }
//
//    @Test
//    public void testIsSquareWithinValidRange_InvalidInput() {
//        boolean result1 = InputHandler.isSquareWithinValidRange("A6", 5);
//        assertFalse(result1); // Out of range
//
//        boolean result2 = InputHandler.isSquareWithinValidRange("C", 5);
//        assertFalse(result2); // Invalid format
//
//        boolean result3 = InputHandler.isSquareWithinValidRange("A0", 5);
//        assertFalse(result3); // Column index should start from 1
//    }
}
