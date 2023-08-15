package sk.itvkurze.Lekcia_15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//class LibraryAppTest {
//    private TitlesPage titlesPage;
//
//    private final String lineSeparator = System.lineSeparator();
//
//    @BeforeEach
//    public void setup() {
//        titlesPage = new TitlesPage(new Scanner(System.in));
//        titlesPage.addBookTitle("BookName,BookAuthor,ISBN1234,200,5");
//        titlesPage.addDVDTitle("DVDName,DVDAuthor,5,120,4");
//    }
//
//    @Test
//    public void testShowAllTitles() {
//        String expectedOutput = "All Titles:\n" +
//                "Name: BookName - Author: BookAuthor | ISBN: ISBN1234 | Number of pages: 200 | Available copies: 5\n" +
//                "Name: DVDName - Author: DVDAuthor - Number of chapters: 5 - Length in minutes: 120 | Available copies: 4\n";
//        assertEquals(expectedOutput, titlesPage.showAlltitles());
//    }
//}
