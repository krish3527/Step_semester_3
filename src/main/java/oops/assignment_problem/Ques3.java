package oops.assignment_problem;

class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    // Constructor
    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Method to allot a vehicle
    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    // Finds the first available parking slot
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] != null && slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }

        return null;
    }

    // Safely allots a vehicle
    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        // Check for null before using slot
        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {

        // Case 1: An available slot exists
        ParkingSlot[] slots1 = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        System.out.println("Slots: A1 (3/4), A2 (5/5)");
        safeAllot(slots1, "TN09AB1234");


        // Case 2: Every slot is full
        ParkingSlot[] slots2 = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        System.out.println("\nSlots: A1 (4/4), A2 (5/5)");
        safeAllot(slots2, "TN09AB1234");
    }
}