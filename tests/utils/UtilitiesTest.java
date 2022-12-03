package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void validRangeWorksWithPositiveTestData(){
        assertTrue(Utilities.validateCostRange(1, 1, 1));
        assertTrue(Utilities.validateCostRange(1, 1, 2));
        assertTrue(Utilities.validateCostRange(1, 0, 1));
        assertTrue(Utilities.validateCostRange(1, 0, 2)) ;
        assertTrue(Utilities.validateCostRange(-1, -2, -1)) ;
    }

    @Test
    void validRangeWorksWithNegativeTestData(){
        assertFalse(Utilities.validateCostRange(1,0,0));
        assertFalse(Utilities.validateCostRange(1,1,0));
        assertFalse(Utilities.validateCostRange(1,2,1));
        assertFalse(Utilities.validateCostRange(-1, -1, -2)) ;
    }

    @Test
    void truncateStringMethodTrucatesCorrectly(){
        assertEquals("123456789", Utilities.truncateString("1234567890", 9));
        assertEquals("1234567890", Utilities.truncateString("1234567890", 10));
        assertEquals("1234567890", Utilities.truncateString("1234567890", 11));
        assertEquals("", Utilities.truncateString("1234567890", 0));
        assertEquals("", Utilities.truncateString("", 0));
        assertEquals("", Utilities.truncateString("", 10));
    }
}