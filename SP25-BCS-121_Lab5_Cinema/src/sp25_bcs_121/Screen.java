package sp25_bcs_121;

public class Screen {
    private Seat[][] seats;
    private String screenName;

    public Screen(String name, int rows) {
        this.screenName = name;
        seats = new Seat[rows][];

        for (int r = 0; r < seats.length; r++) {
            seats[r] = new Seat[4 + r];
            for (int c = 0; c < seats[r].length; c++) {
                String id = String.format("%d-%03d", r + 1, c + 1);
                if (r == 0)
                    seats[r][c] = new Seat(id, Seat.seatType.REGULAR, 500, true);
                else if (r == 1)
                    seats[r][c] = new Seat(id, Seat.seatType.PREMIUM, 750, true);
                else if (r == 2)
                    seats[r][c] = new Seat(id, Seat.seatType.VIP, 1000, true);
                else
                    seats[r][c] = new Seat(id, Seat.seatType.RECLINER, 1200, true);
            }
        }
    }

    public String getName() {
        return screenName;
    }

    public boolean bookSeat(String id) {
        for (int r = 0; r < seats.length; r++)
            for (int c = 0; c < seats[r].length; c++)
                if (seats[r][c].getSeatID().equals(id))
                    return seats[r][c].bookSeat();
        return false;
    }

    public boolean cancelSeat(String id) {
        for (int r = 0; r < seats.length; r++)
            for (int c = 0; c < seats[r].length; c++)
                if (seats[r][c].getSeatID().equals(id))
                    return seats[r][c].cancelSeat();
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   " + screenName + "\n");
        for (int r = 0; r < seats.length; r++) {
            for (int c = 0; c < seats[r].length; c++) {
                sb.append("      " + seats[r][c].toString() + "\n");
            }
        }
        return sb.toString();
    }
}
