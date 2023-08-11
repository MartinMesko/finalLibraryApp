package sk.itvkurze.Lekcia_21.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.itvkurze.Lekcia_21.src.RentalsPage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryAppTest {
    private RentalsPage rentalsPage;

    private final String lineSeparator = System.lineSeparator();

    @BeforeEach
    public void setup() {
        rentalsPage = new RentalsPage(null); // null je tu v poriadku, pre tento test nepotrebujeme objekt typu Library

        // Nastavenie testovacích súborov so vzorovými údajmi
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("titles.txt"))) {
            writer.write("Title1,Author1,Type1,Description1,5" + lineSeparator);
            writer.write("Title2,Author2,Type2,Description2,0" + lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rentals.txt"))) {
            writer.write("1;1;10/08/2023 10:10:10;20/08/2023 10:10:10" + lineSeparator);
            writer.write("2;2;10/08/2023 10:10:10;20/08/2023 10:10:10" + lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Testuje, či metóda getAvailableCopies s ID titulu 1 vráti správny počet kópií (5)
    @Test
    public void testGetAvailableCopiesWithTitleId1Returns5() {
        assertEquals(5, rentalsPage.getAvailableCopies(1));
    }

    // Testuje, či metóda getAvailableCopies s ID titulu 2 vráti správny počet kópií (0)
    @Test
    public void testGetAvailableCopiesWithTitleId2Returns0() {
        assertEquals(0, rentalsPage.getAvailableCopies(2));
    }

    // Testuje, či kniha s ID 1 bola nedávno prenajatá členom s ID 1
    @Test
    public void testIsRecentlyRentedByMemberReturnsTrue() {
        assertTrue(rentalsPage.isRecentlyRentedByMember(1, 1));
    }

    // Testuje, či kniha s ID 3 nebola prenajatá členom s ID 1 (očakáva sa, že nebola)
    @Test
    public void testIsRecentlyRentedByMemberReturnsFalse() {
        assertFalse(rentalsPage.isRecentlyRentedByMember(3, 1));
    }

    // Testuje, či metóda calculateDueDate správne vypočíta dátum splatnosti na základe aktuálneho dátumu a počtu dní požičania
    @Test
    void testCalculateDueDate() {
        RentalsPage rentalsPage = new RentalsPage(new java.util.Scanner(System.in));
        Date currentDate = new Date();
        int rentalDuration = 5; // napríklad 5 dní

        Date dueDate = rentalsPage.calculateDueDate(currentDate, rentalDuration);
        long expectedTime = currentDate.getTime() + rentalDuration * 24 * 60 * 60 * 1000L;

        assertEquals(expectedTime, dueDate.getTime(), "Expected due date to be 5 days from current date");
    }

    // Testuje, či metóda formatDate správne formátuje dátum podľa špecifikovaného vzoru
    @Test
    void testFormatDate() {
        RentalsPage rentalsPage = new RentalsPage(new java.util.Scanner(System.in));
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String expectedFormat = dateFormat.format(date);

        String result = rentalsPage.formatDate(date);

        assertEquals(expectedFormat, result, "Expected formatted date to match pattern dd/MM/yyyy HH:mm:ss");
    }
}
