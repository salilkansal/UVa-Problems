import java.util.Scanner;

/**
 * @author Salil kansal
 */
public class Uva913 {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        while (s1.hasNext()) {
            long n = s1.nextLong();
            long last_no = ((n + 2) * n) / 2;
            long sum = last_no + last_no - 2 + last_no - 4;
            System.out.println(sum);
        }
    }
}
