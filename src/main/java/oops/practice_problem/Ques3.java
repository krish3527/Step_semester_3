
package oops.practice_problem;

import java.util.ArrayList;

class PatientVitals {

    private ArrayList<Double> readings;

    public PatientVitals(double[] initialReadings) {

        readings = new ArrayList<>();

        if (initialReadings != null) {

            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {

        if (reading > 0 && reading <= 45) {
            readings.add(reading);
        }
    }

    public double getAverage() {

        if (readings.isEmpty()) {
            return 0;
        }

        double sum = 0;

        for (double reading : readings) {
            sum += reading;
        }

        return sum / readings.size();
    }

    public double[] getAllReadings() {

        double[] result = new double[readings.size()];

        for (int i = 0; i < readings.size(); i++) {
            result[i] = readings.get(i);
        }

        return result;
    }
}

public class Ques3 {

    public static void main(String[] args) {

        PatientVitals v = new PatientVitals(
                new double[]{36.5, -2, 37.1}
        );

        double[] readings = v.getAllReadings();

        for (double reading : readings) {
            System.out.print(reading + " ");
        }

        System.out.println();

        readings[0] = 999;

        System.out.println(
                v.getAllReadings()[0]
        );

        System.out.println(
                "Average: " + v.getAverage()
        );

        v.recordReading(40.2);
        v.recordReading(50);

        System.out.println(
                "Average: " + v.getAverage()
        );
    }
}
