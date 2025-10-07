package sp25_bcs_121;

public class Seat {
    private String seatID;
    private double price;
    private boolean isAvailable;
    private seatType type;

    public enum seatType { REGULAR, PREMIUM, VIP, RECLINER }

    public Seat(String ID, seatType type, double price, boolean isAvailable) {
        this.seatID = ID;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getSeatID() {
        return seatID;
    }

    public boolean bookSeat() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public boolean cancelSeat() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }

    public String toString() {
        String status = isAvailable ? "Available" : "Booked";
        return seatID + " (" + type + ") - " + price + " PKR - " + status;
    }
}
