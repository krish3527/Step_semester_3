package oops.assignment_problem;

class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }
}


public class Ques5 {

    // Finds the first available parking slot
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] != null &&
                slots[i].occupiedCount < slots[i].capacity) {

                return slots[i];
            }
        }

        return null;
    }


    // Safely allots a parking slot
    static ParkingSlot safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
            return slot;
        }

        return null;
    }


    // Company employee record
    static class CompanyEmployeeRecord {

        String name;
        String empId;

        // Fields themselves are objects
        Employee employee;
        ParkingSlot slot;

        // Shared by all records
        static int totalRecords = 0;


        CompanyEmployeeRecord(
                String name,
                String empId,
                Employee employee,
                ParkingSlot slot) {

            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;

            totalRecords++;
        }


        String fullProfile() {

            double effectivePay;

            // Manager gets salary + team bonus
            if (employee instanceof ManagerEmployee) {

                effectivePay =
                    ((ManagerEmployee) employee).effectiveSalary();

            } else if (employee instanceof InternEmployee) {

                effectivePay =
                    ((InternEmployee) employee).effectiveSalary();

            } else {

                effectivePay = employee.getSalary();
            }


            if (slot != null) {

                return name
                    + " | Pay: Rs "
                    + effectivePay
                    + " | Slot: "
                    + slot.slotNo;

            } else {

                return name
                    + " | Pay: Rs "
                    + effectivePay
                    + " | Slot: no parking assigned";
            }
        }
    }


    public static void main(String[] args) {

        // =========================================
        // PARKING SLOTS
        // =========================================

        ParkingSlot[] parkingSlots = {

            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };


        // =========================================
        // EMPLOYEES
        // Reusing Employee and ManagerEmployee
        // from F2
        // =========================================

        Employee divya =
            new ManagerEmployee(
                1,
                "Divya",
                70000,
                8000
            );

        Employee karan =
            new Employee(
                2,
                "Karan",
                40000
            );

        Employee meera =
            new Employee(
                3,
                "Meera",
                10000
            );


        // =========================================
        // PARKING ALLOCATION
        // =========================================

        ParkingSlot divyaSlot =
            safeAllot(
                parkingSlots,
                "TN09AB1234"
            );


        ParkingSlot karanSlot =
            safeAllot(
                parkingSlots,
                "TN09CD5678"
            );


        // Meera intentionally has no parking
        ParkingSlot meeraSlot = null;


        // =========================================
        // CREATE EMPLOYEE RECORDS
        // =========================================

        CompanyEmployeeRecord record1 =
            new CompanyEmployeeRecord(
                "Divya",
                "E101",
                divya,
                divyaSlot
            );


        CompanyEmployeeRecord record2 =
            new CompanyEmployeeRecord(
                "Karan",
                "E102",
                karan,
                karanSlot
            );


        CompanyEmployeeRecord record3 =
            new CompanyEmployeeRecord(
                "Meera",
                "E103",
                meera,
                meeraSlot
            );


        // =========================================
        // PRINT PROFILES
        // =========================================

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());


        // =========================================
        // TOTAL RECORDS
        // =========================================

        System.out.println(
            "Total records: "
            + CompanyEmployeeRecord.totalRecords
        );
    }
}