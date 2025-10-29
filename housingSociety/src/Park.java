public class Park {

    private String parkID;
    private double width;
    private double depth;

    public Park(String id, double width, double depth) {
        this.parkID = id;
        this.width = width;
        this.depth = depth;
    }

    public double getArea() {
        return width * depth;
    }

    @Override
    public String toString() {
        return "Park " + parkID + " | Area: " + getArea() + " sq.units (" + width + "x" + depth + ")";
    }
}
