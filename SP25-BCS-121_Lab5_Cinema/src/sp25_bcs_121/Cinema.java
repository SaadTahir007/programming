package sp25_bcs_121;

public class Cinema {
    private String name;
    private Screen[] screens;

    public Cinema(String name, int numScreens) {
        this.name = name;
        screens = new Screen[numScreens];
        for (int i = 0; i < screens.length; i++) {
            screens[i] = new Screen("Screen-" + (i + 1), 4);
        }
    }

    public String getName() {
        return name;
    }

    public boolean bookSeat(String screenName, String seatID) {
        for (int i = 0; i < screens.length; i++)
            if (screens[i].getName().equals(screenName))
                return screens[i].bookSeat(seatID);
        return false;
    }

    public boolean cancelSeat(String screenName, String seatID) {
        for (int i = 0; i < screens.length; i++)
            if (screens[i].getName().equals(screenName))
                return screens[i].cancelSeat(seatID);
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ " + name + " ]\n");
        for (int i = 0; i < screens.length; i++) {
            sb.append(screens[i].toString());
        }
        return sb.toString();
    }
}
