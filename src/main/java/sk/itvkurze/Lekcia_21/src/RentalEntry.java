package sk.itvkurze.Lekcia_21.src;

public class RentalEntry {
    private String memberId;
    private String titleId;
    private String rentalDate;
    private String dueDate;

    public RentalEntry(String memberId, String titleId, String rentalDate, String dueDate) {
        this.memberId = memberId;
        this.titleId = titleId;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getTitleId() {
        return titleId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getDueDate() {
        return dueDate;
    }
}
