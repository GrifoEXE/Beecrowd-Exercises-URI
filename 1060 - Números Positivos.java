import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);
        ArrayList<Float> N = new ArrayList<Float>();
        int total = 0;
        while(N.size() <= 5) {
            float numero = s.nextFloat();
            N.add(numero);
        }
        for (int i = 0; i < 6; i++)
        {
            if (N.get(i) > 0)
            {
                total++;
            }
        }
        System.out.println(total + " valores positivos");
    }


}
