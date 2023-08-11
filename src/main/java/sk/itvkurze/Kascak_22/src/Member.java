package sk.itvkurze.Kascak_22.src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {
    private String firstName;
    private String lastName;
    private String documentNumber;
    private Date dateOfBirth;
    private int personalId;
    private List<String> rentedTitles;

    public Member(String firstName, String lastName, String documentNumber, Date dateOfBirth, int personalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.dateOfBirth = dateOfBirth;
        this.personalId = personalId;
        this.rentedTitles = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getPersonalId() {
        return personalId;
    }

    public List<String> getRentedTitles() {
        return rentedTitles;
    }

    public void rentTitle(String title) {
        rentedTitles.add(title);
    }

    public void returnTitle(String title) {
        rentedTitles.remove(title);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = dateFormat.format(dateOfBirth);

        return firstName + " " + lastName + " | Date of Birth: " + dateString + " - Personal Id: " + personalId;
    }
}
