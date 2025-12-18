public class Rack {
    private String rackID;
    private Slot[] slots;
    private double maxWeightCapacity;
    private double currentWeight;
    private int totalSlots;

    public Rack(String rackID, int totalSlots) {
        this.rackID = rackID;
        this.totalSlots = totalSlots;
        this.slots = new Slot[totalSlots];
        this.maxWeightCapacity = 1000.0;
        this.currentWeight = 0.0;

        for (int i = 0; i < totalSlots; i++) {
            slots[i] = new Slot(i + 1);
        }
    }

    public boolean canStoreBag(double weight) {
        return (currentWeight + weight) <= maxWeightCapacity;
    }

    public int findEmptySlot() {
        for (int i = 0; i < totalSlots; i++) {
            if (slots[i].isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public boolean storeBag(Bag bag, int slotIndex) {
        if (slotIndex >= 0 && slotIndex < totalSlots && slots[slotIndex].isEmpty()) {
            if (canStoreBag(bag.getWeight())) {
                slots[slotIndex].storeBag(bag);
                currentWeight += bag.getWeight();
                return true;
            }
        }
        return false;
    }

    public Bag removeBag(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < totalSlots) {
            Bag bag = slots[slotIndex].removeBag();
            if (bag != null) {
                currentWeight -= bag.getWeight();
            }
            return bag;
        }
        return null;
    }

    public int getEmptySlotCount() {
        int count = 0;
        for (Slot slot : slots) {
            if (slot.isEmpty()) count++;
        }
        return count;
    }

    public String getRackID() {
        return rackID;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public double getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public int getTotalSlots() {
        return totalSlots;
    }
}