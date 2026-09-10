package oops.assignment_problem;

public class Ques3 {

    static class Canteen implements Comparable<Canteen> {

        private String canteenCode;
        private String canteenName;
        private int trustScore;

        public Canteen(String canteenCode, String canteenName, int trustScore) {
            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        public int compareTo(Canteen other) {

            if (this.trustScore != other.trustScore) {
                return other.trustScore - this.trustScore;
            }

            int codeCompare =
                    this.canteenCode.compareToIgnoreCase(other.canteenCode);

            if (codeCompare != 0) {
                return codeCompare;
            }

            return this.canteenName.length() - other.canteenName.length();
        }
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        for (int i = 0; i < canteens.length - 1; i++) {

            int best = i;

            for (int j = i + 1; j < canteens.length; j++) {

                if (canteens[j].compareTo(canteens[best]) < 0) {
                    best = j;
                }
            }

            Canteen temp = canteens[i];
            canteens[i] = canteens[best];
            canteens[best] = temp;
        }

        return canteens;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen c : ranked) {
            System.out.println(c.canteenCode);
        }
    }
}