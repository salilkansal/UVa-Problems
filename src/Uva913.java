import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Uva913 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("test_cases/913.txt"));
        Scanner s1 = new Scanner(System.in);
        while (s1.hasNext()) {
            long n = s1.nextLong();
            long last_no = ((n + 2) * n) / 2;
            long sum = last_no + last_no - 2 + last_no - 4;
            System.out.println(sum);

        }

    }
}
