import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int testes = s.nextInt();
        int x = 0;
        int y = 0;

        for(int i = 0; i < testes; i++)
        {
            x = s.nextInt();
            y = s.nextInt();
            System.out.println(jogo(x,y));
        }
    }
    public static int jogo(int figurasR, int figurasV) {
        // a recursividade é interrompida quando figurasV for igual a 0
        if(figurasV == 0){
            return figurasR;
        }
        else{
            return jogo(figurasV, figurasR % figurasV); // efetua uma nova chamada recursiva
        }
    }
}
