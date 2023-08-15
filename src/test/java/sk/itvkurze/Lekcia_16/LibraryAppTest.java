package sk.itvkurze.Lekcia_16;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryAppTest {

    private File testFile;

    @BeforeEach
    public void setUp() {
        testFile = new File("testFile.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("Author,Title,100,ISBN001,5,10");
            writer.newLine();
            // Pridaj ďalšie riadky podľa potreby
        } catch (IOException e) {
            fail("Chyba pri vytváraní testovacieho súboru: " + e.getMessage());
        }

        // Ak je potrebné inicializovať iné komponenty, urobte to tu.
    }

    @AfterEach
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void someTest() {

        assertTrue(testFile.exists(), "Testovací súbor by mal existovať");
    }

}
