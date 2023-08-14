package sk.itvkurze.Lekcia_15;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

// TODO: musíme vymyslieť ešte názvy jednotlivých testov pre anotáciu @DisplayName


class LibraryAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    //MM Testovanie, či správna chybová správa je zobrazená pre neplatný číselný vstup
    @ParameterizedTest
    @CsvSource({
            "6, Please enter a number in the range from 1 to 5",
            "7, Please enter a number in the range from 1 to 5",
            "8, Please enter a number in the range from 1 to 5"
    })
    void whenInputIsNotCorrectThenOutputShowsMessageTest(String input, String expectedResult) {
        String lineSeparator = System.getProperty("line.separator");
        provideInput(input + lineSeparator + "5" + lineSeparator);
        LibraryApp.main(new String[]{});
        assertTrue(outContent.toString().contains(expectedResult));
    }

    //MM Testovanie, či je správna chybová správa zobrazená pre nečíselné neplatné vstupy
    @ParameterizedTest
    @CsvSource({
            "A, Please enter a valid value",
            "a, Please enter a valid value",
            "., Please enter a valid value"
    })

    void whenInputIsNotCorrect2ThenOutputShowsMessageTest(String input, String expectedResult) {
        String lineSeparator = System.getProperty("line.separator");
        provideInput(input + lineSeparator + "5" + lineSeparator);
        LibraryApp.main(new String[]{});
        assertTrue(outContent.toString().contains(expectedResult));
    }

    //MM Testovanie, či výstup zobrazuje správny text pre platný vstup
    @Test
    void whenInputIsCorrectThenOutputShowsCorrectPageTest() {
        String lineSeparator = System.getProperty("line.separator");
        String input = "5" + lineSeparator;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LibraryApp.main(new String[]{});

        String expectedOutput = "1 - Titles" + lineSeparator +
                "2 - Members" + lineSeparator +
                "3 - Rentals" + lineSeparator +
                "4 - Messages" + lineSeparator +
                "5 - Exit" + lineSeparator;
        Assertions.assertTrue(outContent.toString().contains(expectedOutput));
    }

    //MM Obnovenie pôvodného výstupného prúdu po každom teste
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    //MM Pomocná metóda pre poskytovanie vstupných dát jednotlivým testom
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

}
