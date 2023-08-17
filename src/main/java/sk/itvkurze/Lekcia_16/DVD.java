package sk.itvkurze.Lekcia_16;

public class DVD {
    private String authorName;
    private String title;
    private int durationInMinutes;
    private int numberOfTracks;
    private int availableCopies;


    // konštruktor
    public DVD(String title, String authorName,int numberOfTracks , int durationInMinutes, int availableCopies) {
        this.authorName = authorName;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.numberOfTracks = numberOfTracks;
        this.availableCopies = availableCopies;

    }

    // getter a setter metódy
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


}


