public class Plot{

	private String ID;
	private plotType type;
	private boolean available;
	private double price;

	public Enum plotType{RES_5_MARLA, RES_10_MARLA, RES_1_KANAL, PARKING, COMM_SHOP, COMM_OFFICE}


	public Plot(String ID, plotType type, double price, boolean Available) {
        this.plotID = ID;
        this.type = type;
        this.price = price;
        this.available = available;
    }


	public boolean bookPlot() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }

	public boolean cancelPlot() {
        if (!available) {
            available = true;
            return true;
        }
        return false;
    }

    	public String toString() {
        String status = available ? "available" : "Booked";
        return plotID + " (" + type + ") - " + price + " PKR - " + status;
    }
}

}
