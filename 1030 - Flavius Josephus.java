import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static int josephus(int n, int k){
        if (n == 1) {
            return 0;
        }
        return (josephus(n - 1, k) + k) % n;
    }

    public static void main(String[] args) throws IOException {


        Scanner s = new Scanner(System.in);

        int NC = s.nextInt(); //Casos de teste

        for (int i = 1; i <= NC; i++) {
           int n = s.nextInt();
           int k = s.nextInt();
            System.out.println("Case " + i + ": " + (josephus(n, k) + 1));
        }


    }

}
