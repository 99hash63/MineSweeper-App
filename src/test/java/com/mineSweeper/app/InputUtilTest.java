package com.mineSweeper.app;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilTest {

    @Test
    void testGetIntInput_ValidInput() {
        Scanner scanner = new Scanner("5");
        int result = InputUtil.getIntInput(scanner);
        assertEquals(5, result);
    }

    @Test
    void testGetIntInput_NonIntegerInput() {
        Scanner scanner = new Scanner("abc\nfff\nd\n2");
        int result = InputUtil.getIntInput(scanner);
        assertEquals(2, result);
    }

    @Test
    void testIsSquareWithinValidRange_ValidInput() {
        boolean result1 = InputUtil.isValidSquareInput("A1", 5);
        assertTrue(result1);

        boolean result2 = InputUtil.isValidSquareInput("E5", 5);
        assertTrue(result2);
    }

    @Test
    void testIsSquareWithinValidRange_InvalidInput() {
        boolean result1 = InputUtil.isValidSquareInput("A6", 5);
        assertFalse(result1); // Out of range

        boolean result2 = InputUtil.isValidSquareInput("C", 5);
        assertFalse(result2); // Invalid format

        boolean result3 = InputUtil.isValidSquareInput("A0", 5);
        assertFalse(result3); // Column index should start from 1
    }
}
