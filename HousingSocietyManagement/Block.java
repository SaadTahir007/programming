public class Block{

	private String blockName;
	private Plot[] plots;
	
	public Block(String name, int streets){
		this.blockName = name;
		plots = new Plot[streets][];	
		
		for (int r = 0; r < plots.length; r++) {
            		plots[r] = new Plot[5];
            		for (int c = 0; c < plots[r].length; c++) {
                		String id = String.format("%d-%03d", r + 1, c + 1);
				if (r == 0)
                    			plots[r][c] = new Plot(id, Plot.plotType.RES_5_MARLA, 4,000,000, true);
				if (r == 1)
                    			plots[r][c] = new Plot(id, Plot.plotType.RES_10_MARLA, 7,500,000, true);
				if (r == 2)
                    			plots[r][c] = new Plot(id, Plot.plotType.RES_1_KANAL, 14,000,000, true);	
				if (r == 3)
                    			plots[r][c] = new Plot(id, Plot.plotType.COMM_SHOP, 3,000,000, true);
				else
                    			plots[r][c] = new Plot(id, Plot.plotType.COMM_OFFICE, 5,000,000, true);

                		
            		}
        	}

	public boolean bookPlot(String id) {
        	for (int r = 0; r < plots.length; r++)
           		for (int c = 0; c < plots[r].length; c++)
                		if (plots[r][c].getPlotID().equals(id))
                    			return plots[r][c].bookPlot();
        	return false;
    }

    	public boolean cancelPlot(String id) {
        	for (int r = 0; r < plots.length; r++)
            		for (int c = 0; c < plots[r].length; c++)
                		if (plots[r][c].getPlotID().equals(id))
                    			return plots[r][c].cancelPlot();
        	return false;
    }

    	public String toString() {
        	StringBuilder sb = new StringBuilder();
        	sb.append("   " + blockName + "\n");
        	for (int r = 0; r < plots.length; r++) {
            		for (int c = 0; c < plots[r].length; c++) {
                		sb.append("      " + plots[r][c].toString() + "\n");
            		}
        	}
        	return sb.toString();
    	}


}