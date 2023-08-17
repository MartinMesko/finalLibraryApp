package sk.itvkurze.Lekcia_16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryAppTest {

    private TitlesPage titlesPage;

    @BeforeEach
    public void setUp() {
        titlesPage = new TitlesPage();
    }

    @Test
    public void testSaveBookSuccessfully() {
        Book newBook = new Book("NewTitle", "NewAuthor", "ISBN002", 150, 5);
        boolean result = titlesPage.saveTitle(newBook);
        assertTrue(result, "Ukladanie knihy malo prebehnúť úspešne");


    }

    @Test
    public void testSaveDVDSuccessfully() {
        DVD newDVD = new DVD("NewTitle", "NewAuthor", 7, 120, 3);
        boolean result = titlesPage.saveTitle(newDVD);
        assertTrue(result, "Ukladanie DVD malo prebehnúť úspešne");


    }
}
