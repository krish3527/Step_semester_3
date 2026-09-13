package oops.practice_problem;

import java.util.regex.Pattern;

interface DischargeRecord {
    String[] getMedicationCodes();
}

final class DischargeSummary implements DischargeRecord {

    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(
            String patientId,
            String[] medicationCodes) {

        if (medicationCodes == null) {
            throw new IllegalArgumentException(
                    "Invalid medication codes"
            );
        }

        for (String code : medicationCodes) {

            if (code == null ||
                !Pattern.matches("MED-[A-Z]", code)) {

                throw new IllegalArgumentException(
                        "Invalid medication code"
                );
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index,
            String newCode) {

        if (index < 0 ||
            index >= medicationCodes.length) {

            throw new IndexOutOfBoundsException(
                    "Invalid index"
            );
        }

        if (newCode == null ||
            !Pattern.matches("MED-[A-Z]", newCode)) {

            throw new IllegalArgumentException(
                    "Invalid medication code"
            );
        }

        String[] correctedCodes =
                medicationCodes.clone();

        correctedCodes[index] = newCode;

        return new DischargeSummary(
                patientId,
                correctedCodes
        );
    }
}

class CriticalCareDischargeSummary
        implements DischargeRecord {

    private final String patientId;
    private final String[] medicationCodes;
    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        if (medicationCodes == null) {
            throw new IllegalArgumentException(
                    "Invalid medication codes"
            );
        }

        for (String code : medicationCodes) {

            if (code == null ||
                !Pattern.matches("MED-[A-Z]", code)) {

                throw new IllegalArgumentException(
                        "Invalid medication code"
                );
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
        this.icuDays = icuDays;
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }
}

public class Ques5 {

    static {
        System.out.println(
                "Discharge Ledger Initialized"
        );
    }

    static String processNightlyBatch(
            DischargeRecord[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeRecord summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else if (summary instanceof DischargeSummary) {
                routine++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               criticalCare + " critical-care | " +
               routine + " routine";
    }

    public static void main(String[] args) {

        try {

            new DischargeSummary(
                    "MT2026-0142",
                    new String[]{"MED-A", "bad"}
            );

            System.out.println(
                    "construction succeeded"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "construction rejected"
            );
        }

        DischargeSummary d =
                new DischargeSummary(
                        "MT2026-0142",
                        new String[]{"MED-A", "MED-B"}
                );

        String[] codes = d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
                d.getMedicationCodes()[0]
        );

        DischargeRecord[] summaries = {

                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4
                ),

                null,

                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"}
                )
        };

        System.out.println(
                processNightlyBatch(summaries)
        );
    }
}