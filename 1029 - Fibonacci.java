import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int num_calls = 0;
    public static int fibonacci(int num)
    {
        num_calls++;
        if (num < 2 )
        {
            return 1;
        }
        return fibonacci(num - 1) + fibonacci(num - 2);

    }
    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        int casosTeste = s.nextInt();

        for(int i=0 ; i < casosTeste; i++)
        {
            int num = s.nextInt();
            fibonacci(num);
            System.out.println("fib(" + num + ") = " + (num_calls - 1) + " calls = "+ fibonacci(num-1));
            num_calls = 0;
        }
    }
}
