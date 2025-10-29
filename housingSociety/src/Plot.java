public class Plot {

    public enum PlotType {
        RES_5_MARLA, RES_10_MARLA, RES_1_KANAL, PARKING, COMM_SHOP, COMM_OFFICE
    }

    public enum ShapeType {
        RECTANGLE, TRAPEZOID, L_SHAPE
    }

    private String plotID;
    private PlotType type;
    private boolean available;
    private double price;

    private ShapeType shape;
    private double width;
    private double depth;
    private double front;
    private double back;
    private double w1;
    private double d1;
    private double w2;
    private double d2;
    private double area;

    public Plot(String plotID,
                PlotType type, ShapeType shape, double width, double depth, double front, double back, double w1, double d1, double w2, double d2, boolean 		available) {

        this.plotID = plotID;
        this.type = type;
        this.shape = shape;
        this.width = width;
        this.depth = depth;
        this.front = front;
        this.back = back;
        this.w1 = w1;
        this.d1 = d1;
        this.w2 = w2;
        this.d2 = d2;
        this.available = available;

        this.area = computeArea();
        this.price = computeBasePrice(type);
    }

    private double computeBasePrice(PlotType t) {
        switch (t) {
            case RES_5_MARLA: return 4_000_000;
            case RES_10_MARLA: return 7_500_000;
            case RES_1_KANAL: return 14_000_000;
            case COMM_SHOP: return 3_000_000;
            case COMM_OFFICE: return 5_000_000;
            case PARKING: return 200_000;
            default: return 0;
        }
    }

    private double computeArea() {
        switch (shape) {
            case RECTANGLE:
                return width * depth;
            case TRAPEZOID:
                return ((front + back) / 2.0) * depth;
            case L_SHAPE:
                return (w1 * d1) + (w2 * d2);
            default:
                return 0;
        }
    }

    public String getPlotID() { return plotID; }
    public PlotType getType() { return type; }
    public ShapeType getShape() { return shape; }
    public double getArea() { return area; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }

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

    @Override
    public String toString() {
        String formattedPrice = String.format("%,.0f PKR", getPrice());
        String status = isAvailable() ? "Available" : "Booked";
        return String.format(
            "ID: %-6s | Type: %-15s | Shape: %-9s | Area: %7.1f su | Price: %-14s | %s",
            plotID, type, shape, area, formattedPrice, status
        );
    }
}
