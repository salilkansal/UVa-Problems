import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class UnitTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Test
    public void Uva913() throws Exception {
        System.setIn(new FileInputStream("test_cases/913.txt"));
        System.setOut(new PrintStream(outContent));
        Uva913.main(null);
        assertEquals(outContent.toString(), "15\n45\n87\n");
        System.setIn(null);
        System.setOut(null);
    }

    @Test
    public void Uva11838() throws Exception {
        System.setIn(new FileInputStream("test_cases/11838.txt"));
        System.setOut(new PrintStream(outContent));
        Uva11838.main(null);
        assertEquals(outContent.toString(), "1\n1\n0\n0\n");
        System.setIn(null);
        System.setOut(null);
    }

}