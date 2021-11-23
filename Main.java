import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.constant.Constable;
import java.util.Scanner;
import java.util.Stack;

/**
 * IMPORTANT: 
 *      O nome da classe deve ser "Main" para que a sua solução execute
 *      Class name must be "Main" for your solution to execute
 *      El nombre de la clase debe ser "Main" para que su solución ejecutar
 *
 *      # N <= 1000 (máximo de 1k vagões) numerados SEMPRE em ordem crescente (neste caso o primeiro a chegar
 *      é sempre o 1, depois o 2, depois o 3...)
 *
 */
public class Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {

        String l;
        String[] coaches;
        int N, current, coach;
        boolean firstTest = true;
        while (!(l = in.readLine()).equals("0")) {
            N = Integer.parseInt(l);
            if (firstTest) {
                firstTest = false;
            } else {
                out.println();
            }
            while (!(l = in.readLine()).equals("0")) {
                Pilha stack = new Pilha();
                coaches = l.split("\\s");
                current = 0;
                coach = Integer.parseInt(coaches[current]);
                for (int i = 1; i <= N; i++) {
                    stack.push(i);

                    while (!stack.empty() && coach == (int) stack.top()) {
                        if (++current < N) {
                            coach = Integer.parseInt(coaches[current]);
                        }
                        stack.pop();
                    }
                }
                out.println(stack.empty() ? "Yes" : "No");
            }
        }
        out.println();
        out.close();


    }

}
class Pilha{
    private int array [ ];
    private int topo ;
    static private final int DEFAULT = 1000;

    public Pilha () {
        array = new int [DEFAULT] ;
        topo = (-1);
    }
    public void esvazie ( ) { topo = (-1); }
    public int tamanho ( ) { return (topo + 1) ; }

    public boolean empty(){return topo == -1 ;}

    public void push (int x )
    {
        topo++;
        if (topo == array.length)
            dupliqueArray();
        array[topo] = x;
    }

    private void dupliqueArray(){
        int[] novoarray = new int[2 * array.length];

        for (int i = 0; i < array.length; i++)
        {
            novoarray[i] = array[i];
        }
        array = novoarray;
    }

    public Object top()
    {
        if (topo >= 0)
        {
            return (array[topo]);
        }
        else return (null);
    }

    public Object pop()
    {
        if(topo>= 0)
        {
            return(array[topo--]);
        }
        else return (null);
    }
}