package sp25_bcs_121;

public class CinemaDemo {
    public static void main(String[] args) {
        System.out.println("===== Cinema Management System =====\n");

        CityCinema karachi = new CityCinema("Karachi", 2);
        CityCinema lahore = new CityCinema("Lahore", 2);
        CityCinema islamabad = new CityCinema("Islamabad", 1);

        System.out.println(karachi);
        System.out.println(lahore);
        System.out.println(islamabad);

        System.out.println("\n--- Booking Seat 1-001 in Karachi Cinema-1, Screen-1 ---");
        karachi.bookSeat("Karachi Cinema-1", "Screen-1", "1-001");
        System.out.println(karachi);

        System.out.println("\n--- Canceling Seat 1-001 in Karachi Cinema-1 ---");
        karachi.cancelSeat("Karachi Cinema-1", "Screen-1", "1-001");
        System.out.println(karachi);

        System.out.println("\n--- Booking Seat 1-002 in Lahore Cinema-2, Screen-1 ---");
        lahore.bookSeat("Lahore Cinema-2", "Screen-1", "1-002");
        System.out.println(lahore);
    }
}
