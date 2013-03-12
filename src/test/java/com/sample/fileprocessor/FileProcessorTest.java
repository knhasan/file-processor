package com.sample.fileprocessor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for FileProcessor.
 */
public class FileProcessorTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private static final StringBuilder sb = new StringBuilder();

    @BeforeClass
    public static void buildOutput() {
        sb.append("CATEGORY   COUNT\n");
        sb.append("PERSON   2\n");
        sb.append("PLACE   2\n");
        sb.append("ANIMAL   2\n");
        sb.append("COMPUTER   1\n");
        sb.append("OTHER   1\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("PERSON Bob Jones\n");
        sb.append("PLACE Washington\n");
        sb.append("PERSON Mary\n");
        sb.append("COMPUTER Mac\n");
        sb.append("OTHER Tree\n");
        sb.append("ANIMAL Dog\n");
        sb.append("PLACE Texas\n");
        sb.append("ANIMAL Cat\n");
    }
    
    @Before
    public void setSystemIO() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }
    
    @Test
    public void testFileProcessor() {
        FileProcessor.main(new String[]{"src/test/resources/testinput.txt"});
        assertEquals(sb.toString(), out.toString());
    }
    
    @Test
    public void testFileProcessorEmptyInput() {
        FileProcessor.main(new String[]{});
        assertEquals("Usage: java com.sample.FileProcessor <path_to_input_file>\n", out.toString());
    }
    
    @Test
    public void testFileProcessorInvalidInput() {
        FileProcessor.main(new String[]{"invalidfile.none"});
        assertEquals("Usage: java com.sample.FileProcessor <path_to_input_file>\n", out.toString());
    }
    
    @After
    public void clearSystemIO() {
        System.setOut(null);
        System.setErr(null);
    }
    
}
