public class CornerPlot extends Plot {

    private double secondFrontWidth;
    private double cornerPremium = 0.08; // +8%

    public CornerPlot(String plotID,
                      PlotType type,
                      double firstFrontWidth,
                      double depth,
                      double secondFrontWidth,
                      boolean available) {

        super(plotID, type, ShapeType.L_SHAPE, 0, 0, 0, 0, firstFrontWidth, depth, secondFrontWidth, depth, available);

        this.secondFrontWidth = secondFrontWidth;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1.0 + cornerPremium);
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("%,.0f PKR", getPrice());
        String status = isAvailable() ? "Available" : "Booked";
        // reuse getType() and getArea()
        return String.format(
            "ID: %-6s | Type: %-15s | Shape: %-9s | Area: %7.1f su | Price: %-14s | %s (Corner +8%%)",
            getPlotID(), getType(), getShape(), getArea(), formattedPrice, status
        );
    }
}
