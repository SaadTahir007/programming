public class Block {

    private String blockName;
    private Plot[][] plots;
    private Park[] parks;
    private CommercialMarket market;

    public Block(String name, int streets) {
        this.blockName = name;
        this.plots = new Plot[streets][];

        for (int r = 0; r < plots.length; r++) {
            int streetLength = 10 + r;
            plots[r] = new Plot[streetLength];

            for (int c = 0; c < plots[r].length; c++) {
                String id = String.format("%d-%03d", r + 1, c + 1);

                if ((c + 1) % 5 == 0) {
                    plots[r][c] = new Plot(id, Plot.PlotType.PARKING, Plot.ShapeType.RECTANGLE, 3, 6, 0, 0, 0, 0, 0, 0, true);
                    continue;
                }

                boolean makeCorner = (r <= 2) && ((c + 1) % 4 == 0);

                if (r == 0) {
                    if (makeCorner) {
                        plots[r][c] = new CornerPlot(id, Plot.PlotType.RES_5_MARLA, 20, 45, 6, true);
                    } else {
                        plots[r][c] = new Plot(id, Plot.PlotType.RES_5_MARLA, Plot.ShapeType.RECTANGLE, 20, 45, 0, 0, 0, 0, 0, 0, true);
                    }
                } else if (r == 1) {
                    if (makeCorner) {
                        plots[r][c] = new CornerPlot(id, Plot.PlotType.RES_10_MARLA, 30, 60, 8, true);
                    } else {
                        plots[r][c] = new Plot(id, Plot.PlotType.RES_10_MARLA, Plot.ShapeType.RECTANGLE, 30, 60, 0, 0, 0, 0, 0, 0, true);
                    }
                } else if (r == 2) {
                    if (makeCorner) {
                        plots[r][c] = new CornerPlot(id, Plot.PlotType.RES_1_KANAL, 35, 80, 12, true);
                    } else {
                        plots[r][c] = new Plot(id, Plot.PlotType.RES_1_KANAL, Plot.ShapeType.TRAPEZOID, 0, 80, 35, 45, 0, 0, 0, 0, true);
                    }
                } else if (r == 3) {
                    plots[r][c] = new Plot(id, Plot.PlotType.COMM_SHOP, Plot.ShapeType.RECTANGLE, 10, 25, 0, 0, 0, 0, 0, 0, true);
                } else {
                    plots[r][c] = new Plot(id, Plot.PlotType.COMM_OFFICE, Plot.ShapeType.RECTANGLE, 20, 30, 0, 0, 0, 0, 0, 0, true);
                }
            }
        }


    	this.parks = new Park[2];
    	parks[0] = new Park("Park-A", 50, 40);
    	parks[1] = new Park("Park-B", 60, 50);

	this.market = new CommercialMarket(name + "-Market", 15);


    }

     public boolean bookPlot(String id) {
        for (int r = 0; r < plots.length; r++)
            for (int c = 0; c < plots[r].length; c++)
                if (plots[r][c].getPlotID().equals(id))
                    return plots[r][c].bookPlot();

        return market.bookShop(id);
    }

      public boolean cancelPlot(String id) {
        for (int r = 0; r < plots.length; r++)
            for (int c = 0; c < plots[r].length; c++)
                if (plots[r][c].getPlotID().equals(id))
                    return plots[r][c].cancelPlot();

        return market.cancelShop(id);
    }

   @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n========== Block ").append(blockName).append(" ==========\n");

    for (int r = 0; r < plots.length; r++) {
        sb.append("\nStreet - ").append(r + 1).append("\n");
        sb.append("---------------------------------------------\n");
        for (int c = 0; c < plots[r].length; c++) {
            sb.append(plots[r][c].toString()).append("\n");
        }
    }

    sb.append("\n========== AMENITIES ==========\n");

    sb.append("\n--- Parks ---\n");
    for (Park p : parks) {
        sb.append(p.toString()).append("\n");
    }

    sb.append("\n--- Commercial Market ---\n");
    sb.append(market.toString()).append("\n");

    return sb.toString();
}

}
