package sk.itvkurze.Lekcia_14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void whenInputIsNotCorrectThenOutputShowsMessageTest() {
        String lineSeparator = System.getProperty("line.separator");
        provideInput1("6" + lineSeparator + "5" + lineSeparator);
        LibraryApp.main(new String[]{});
        assertTrue(outContent.toString().contains("Please enter a number in the range from 1 to 5"));
    }

    @Test
    void whenInputIsNotCorrect2ThenOutputShowsMessageTest() {
        String lineSeparator = System.getProperty("line.separator");
        provideInput2("a" + lineSeparator + "5" + lineSeparator);
        LibraryApp.main(new String[]{});
        assertTrue(outContent.toString().contains("Please enter a valid value."));
    }

    @Test
    void whenInputIsCorrectThenOutputShowsCorrectPageTest() {
        String lineSeparator = System.getProperty("line.separator");
        String input = "5" + lineSeparator;
        provideInput3(input);
        LibraryApp.main(new String[]{});

        String expectedOutput = "1 - Titles" + lineSeparator +
                "2 - Members" + lineSeparator +
                "3 - Rentals" + lineSeparator +
                "4 - Messages" + lineSeparator +
                "5 - Exit" + lineSeparator;
        Assertions.assertTrue(outContent.toString().contains(expectedOutput));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    private void provideInput1(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private void provideInput2(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private void provideInput3(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

}
