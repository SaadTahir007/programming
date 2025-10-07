package sp25_bcs_121;

public class CityCinema {
    private String city;
    private Cinema[] cinemas;

    public CityCinema(String cityName, int numberOfCinemas) {
        this.city = cityName;
        cinemas = new Cinema[numberOfCinemas];
        for (int i = 0; i < cinemas.length; i++) {
            cinemas[i] = new Cinema(cityName + " Cinema-" + (i + 1), 2);
        }
    }

    public boolean bookSeat(String cinemaName, String screenName, String seatID) {
        for (int i = 0; i < cinemas.length; i++)
            if (cinemas[i].getName().equals(cinemaName))
                return cinemas[i].bookSeat(screenName, seatID);
        return false;
    }

    public boolean cancelSeat(String cinemaName, String screenName, String seatID) {
        for (int i = 0; i < cinemas.length; i++)
            if (cinemas[i].getName().equals(cinemaName))
                return cinemas[i].cancelSeat(screenName, seatID);
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("City: ").append(city).append("\n");
        for (int i = 0; i < cinemas.length; i++) {
            sb.append(cinemas[i].toString());
        }
        return sb.toString();
    }
}
