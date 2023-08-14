package sk.itvkurze.Lekcia_15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryAppTest {
    private TitlesPage titlesPage;

    private final String lineSeparator = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        titlesPage = new TitlesPage(null); // null je tu v poriadku, pre tento test nepotrebujeme objekt typu Scanner

        // Nastavenie testovacích súborov so vzorovými údajmi
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("titles.txt"))) {
            writer.write("Book1,Author1,ISBN1,300,5" + lineSeparator);
            writer.write("Book2,Author2,ISBN2,500,3" + lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("titlesDVD.txt"))) {
            writer.write("DVD1,AuthorDVD1,10,90,2" + lineSeparator);
            writer.write("DVD2,AuthorDVD2,15,120,0" + lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testShowAllTitlesWithTitlesAvailable() {
//        List<String> result = titlesPage.showAlltitles();
//
//        assertEquals(5, result.size());
//        assertEquals("All Titles", result.get(0));
//        assertEquals("Name: Book1 - Author: Author1 | ISBN: ISBN1 | Number of pages: 300 | Available copies: 5", result.get(1));
//        assertEquals("Name: Book2 - Author: Author2 | ISBN: ISBN2 | Number of pages: 500 | Available copies: 3", result.get(2));
//        assertEquals("Name: DVD1 - Author: AuthorDVD1 - Number of chapters: 10 - Length in minutes: 90 | Available copies: 2", result.get(3));
//        assertEquals("Name: DVD2 - Author: AuthorDVD2 - Number of chapters: 15 - Length in minutes: 120 | Available copies: 0", result.get(4));
//    }
//
//    @Test
//    public void testShowAllTitlesWhenNoTitlesAvailable() {
//        // Predpokladáme, že ak súbory "titles.txt" a "titlesDVD.txt" neexistujú, vráti "No titles available"
//        List<String> result = titlesPage.showAlltitles();
//
//        assertEquals(1, result.size());
//        assertEquals("No titles available", result.get(0));
//    }
}
