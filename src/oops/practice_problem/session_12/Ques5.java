
package oops.practice_problem.session_12;

import java.io.*;

class Candidate {
    String name;
    int[] scores = new int[6];
    int total, climb, start, end;

    Candidate(String name, int[] scores) {
        this.name = name;
        this.scores = scores;

        for (int s : scores)
            total += s;

        findClimb();
    }

    void findClimb() {
        int current = 0, best = 0, startIndex = 0;
        int bestStart = 0, bestEnd = -1;

        for (int i = 0; i < 5; i++) {
            int diff = scores[i + 1] - scores[i];

            if (current + diff < diff) {
                current = diff;
                startIndex = i;
            } else {
                current += diff;
            }

            if (current > best) {
                best = current;
                bestStart = startIndex;
                bestEnd = i;
            }
        }

        climb = best;

        if (bestEnd == -1) {
            start = 1;
            end = 1;
        } else {
            start = bestStart + 1;
            end = bestEnd + 2;
        }
    }
}

public class Ques5 {

    static void mergeSort(Candidate[] a, Candidate[] temp,
                          int low, int high) {

        if (low >= high)
            return;

        int mid = (low + high) / 2;

        mergeSort(a, temp, low, mid);
        mergeSort(a, temp, mid + 1, high);

        int i = low, j = mid + 1, k = low;

        while (i <= mid && j <= high) {
            if (a[i].total >= a[j].total)
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }

        while (i <= mid)
            temp[k++] = a[i++];

        while (j <= high)
            temp[k++] = a[j++];

        for (i = low; i <= high; i++)
            a[i] = temp[i];
    }

    static int cutoff(Candidate[] a, int x) {
        int low = 0, high = a.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (a[mid].total >= x)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    static String pair(Candidate[] a, int target) {
        int i = 0, j = a.length - 1;

        while (i < j) {
            int sum = a[i].total + a[j].total;

            if (sum == target)
                return a[i].name + " + " + a[j].name;
            else if (sum > target)
                i++;
            else
                j--;
        }

        return "no pair";
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Candidate[] a = new Candidate[n];
        Candidate[] temp = new Candidate[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int[] scores = new int[6];

            for (int j = 0; j < 6; j++)
                scores[j] = Integer.parseInt(st.nextToken());

            a[i] = new Candidate(name, scores);
        }

        mergeSort(a, temp, 0, n - 1);

        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + a[i].name +
                    " " + a[i].total +
                    " | best climb +" + a[i].climb +
                    " (test " + a[i].start + " to " + a[i].end + ")");
        }

        String line;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty())
                continue;

            st = new StringTokenizer(line);

            String command = st.nextToken();
            int x = Integer.parseInt(st.nextToken());

            if (command.equals("CUTOFF")) {
                System.out.println("CUTOFF " + x + ": " +
                        cutoff(a, x) + " candidates");
            } else if (command.equals("PAIR")) {
                System.out.println("PAIR " + x + ": " + pair(a, x));
            }
        }
    }
}
