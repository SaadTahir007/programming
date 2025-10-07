package sp25_bcs_121;

public class SeatDemo {
    public static void main(String[] args) {
        System.out.println("----- Seat Demo -----");

        Seat s1 = new Seat("1-001", Seat.seatType.REGULAR, 500, true);
        Seat s2 = new Seat("1-002", Seat.seatType.VIP, 1000, true);

        System.out.println("Before booking:");
        System.out.println(s1);
        System.out.println(s2);

        s2.bookSeat();
        System.out.println("\nAfter booking VIP seat:");
        System.out.println(s2);

        s2.cancelSeat();
        System.out.println("\nAfter canceling booking:");
        System.out.println(s2);
    }
}
