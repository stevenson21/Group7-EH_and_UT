package com.fh.HA_EH_UT;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogSearcherTest {
    String logDirectory = "logs";
    @Test
    public void testSearchByDate_Found() throws EMSException {
        LogSearcher logSearcher = new LogSearcher(logDirectory, new Metadata());
        List<String> results = logSearcher.searchByDate("2024-10-08");
        assertFalse(results.isEmpty(), "Log files should be found for the date");
        System.out.println();
    }

    @Test
    public void testSearchByDate_NotFound() throws EMSException {
        LogSearcher logSearcher = new LogSearcher(logDirectory, new Metadata());
        List<String> results = logSearcher.searchByDate("1990-01-01");
        assertTrue(results.isEmpty(), "No log files should be found for an old date");
        System.out.println();
    }

    @Test
    public void testSearchByEquipment_Found() throws EMSException {
        LogSearcher logSearcher = new LogSearcher(logDirectory, new Metadata());
        List<String> results = logSearcher.searchByEquipment("solar");
        assertFalse(results.isEmpty(), "Log files should be found for the equipment");
        System.out.println();
    }

    @Test
    public void testOpenLogFile_Success() {
        LogSearcher logSearcher = new LogSearcher(logDirectory, new Metadata());
        assertDoesNotThrow(() -> logSearcher.openLogFile("wind.log"));
        System.out.println();
    }

    @Test
    public void testOpenLogFile_FileNotFound() {
        LogSearcher logSearcher = new LogSearcher(logDirectory, new Metadata());
        EMSException exception = assertThrows(EMSFileNotFoundException.class, () -> logSearcher.openLogFile("nonexistent.log"));
        System.out.println("Exception message: " + exception.getMessage());  // Optional for printing
        assertEquals("Log file does not exist: nonexistent.log", exception.getMessage());
    }

}