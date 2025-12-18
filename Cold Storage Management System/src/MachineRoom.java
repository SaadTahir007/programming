public class MachineRoom {
    private String machineID;
    private boolean isOn;
    private int floorNumber;

    public MachineRoom(String machineID, int floorNumber) {
        this.machineID = machineID;
        this.floorNumber = floorNumber;
        this.isOn = true;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("Machine " + machineID + " is now ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Machine " + machineID + " is now OFF");
    }

    public void displayStatus() {
        System.out.println("\n--- Machine Status ---");
        System.out.println("Machine ID: " + machineID);
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Status: " + (isOn ? "ON" : "OFF"));
    }

    public String getMachineID() {
        return machineID;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
