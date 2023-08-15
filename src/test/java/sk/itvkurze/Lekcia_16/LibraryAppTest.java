package sk.itvkurze.Lekcia_16;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LibraryAppTest {

    private TitlesPage titlesPage;

    @BeforeEach
    void setUp() {
        titlesPage = new TitlesPage();
    }


    @Test
    void testSaveBookTitle() {
        // test adding a book
        boolean result = titlesPage.saveTitle("Test Book", "Test Author", "Book", "123456789,200", 10);
        assertTrue(result);
        assertTrue(new File("titles.txt").exists());
    }

    @Test
    void testSaveDVDTitle() {
        // test adding a DVD
        boolean result = titlesPage.saveTitle("Test DVD", "Test Director", "DVD", "10,120", 5);
        assertTrue(result);
        assertTrue(new File("titlesDVD.txt").exists());
    }

    @Test
    void testSaveInvalidType() {
        // test pridania neplatného typu, mal by štandardne zapisovať do titles.txt
        boolean result = titlesPage.saveTitle("Test Invalid", "Test Author", "InvalidType", "none,none", 1);
        assertTrue(result);
        assertTrue(new File("titles.txt").exists());
    }

    @Test
    void testSaveListUpdate() {
        // test if the lists are updated
        titlesPage.saveTitle("Test Book", "Test Author", "Book", "123456789,200", 10);
        titlesPage.saveTitle("Test DVD", "Test Director", "DVD", "10,120", 5);

        assertTrue(titlesPage.getTitles().contains("Test Book,Test Author,Book,123456789,200,10"));
        assertTrue(titlesPage.getTitlesDVD().contains("Test DVD,Test Director,DVD,10,120,5"));
    }

}
