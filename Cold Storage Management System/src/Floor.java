public class Floor {
    private int floorNumber;
    private Rack[] racks;
    private int totalRacks;

    public Floor(int floorNumber, int totalRacks) {
        this.floorNumber = floorNumber;
        this.totalRacks = totalRacks;
        this.racks = new Rack[totalRacks];

        for (int i = 0; i < totalRacks; i++) {
            String rackID = "F" + floorNumber + "R" + (i + 1);
            racks[i] = new Rack(rackID, 3);
        }
    }

    public int findEmptyRack() {
        for (int i = 0; i < totalRacks; i++) {
            if (racks[i].getEmptySlotCount() > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Rack[] getRacks() {
        return racks;
    }

    public int getTotalRacks() {
        return totalRacks;
    }
}
