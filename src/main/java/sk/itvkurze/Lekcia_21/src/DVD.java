package sk.itvkurze.Lekcia_21.src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DVD {
    private String title;
    private String author;
    private String chapters;
    private String length;
    private String copies;


    public DVD(String title, String author, String chapters, String length, String copies) {
        this.title = title;
        this.author = author;
        this.chapters = chapters;
        this.length = length;
        this.copies = copies;
    }

    public void addDVDToFile() {
        String dvdData = title + "," + author + "," + chapters + "," + length + "," + copies;

        try {
            FileWriter fileWriter = new FileWriter("titlesDVD.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(dvdData);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("DVD not added !!!");
            e.printStackTrace();
        }
    }
}
