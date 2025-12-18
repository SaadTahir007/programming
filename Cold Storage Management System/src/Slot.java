public class Slot {
    private int slotNumber;
    private boolean isEmpty;
    private Bag bagStored;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isEmpty = true;
        this.bagStored = null;
    }

    public boolean storeBag(Bag bag) {
        if (isEmpty) {
            this.bagStored = bag;
            this.isEmpty = false;
            return true;
        }
        return false;
    }

    public Bag removeBag() {
        if (!isEmpty) {
            Bag temp = bagStored;
            bagStored = null;
            isEmpty = true;
            return temp;
        }
        return null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Bag getBagStored() {
        return bagStored;
    }
}