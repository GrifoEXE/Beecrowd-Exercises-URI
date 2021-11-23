import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    static boolean checkParen(String str){
        int len = str.length();
        char c;
        boolean openclose = true;
        PilhaDinamica pilha = new PilhaDinamica();
        for(int i = 0; i < len; i++)
        {
            c = str.charAt(i);
            if(c == '(')
                pilha.push(1);
            else if(c == ')' && pilha.tamanho() > 0)
                pilha.pop();
            else if(c == ')')
            {
                openclose = false;
                pilha.pop();
            }
        }
        return pilha.empty() && openclose;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;

        while((str = br.readLine()) != null)
        {
            if(checkParen(str))
                System.out.println("correct");
            else
                System.out.println("incorrect");
        }




    }

}

class PilhaDinamica //Duplamente Encadeada
{
    public NoInt first;
    public NoInt last;
    private int contador;

    public PilhaDinamica()
    {
        first = null;
        last = null;
        contador = 0;

    }

    public void push(int elemento)
    {
        NoInt novo = new NoInt(elemento);

        if (first == null) //lista vazia
        {
            first = novo;
            last = novo;
            novo.proximo = null;
            novo.anterior = null;
        }
        else
        {
            last.proximo = novo;
            novo.anterior = last;
            novo.proximo = null;
            last = novo;
        }
        contador++;
    }

    public void pop()
    {
        NoInt aux = first;
        if(first == last)
        {
            limpar();
        }
        else
        {
            last = last.anterior;
            last.proximo = null;
            contador--;
        }
    }

    public void limpar()
    {
        contador = 0;
        first = null;
        last = null;
    }

    public boolean contem(int elemento)
    {
        NoInt aux = first;

        while(aux != null)
        {
            if (aux.dado == elemento)
            {
                return true;
            }
            aux = aux.proximo;
        }
        return false;
    }

    public NoInt buscar(int pos) //Terceira Operação Básica
    {
        //Caso da posição ser maior ou igual ao contador
        if(pos >= contador)
        {
            throw new Error("A posição inserida não está na lista");
        }

        //Caso da lista estar vazia
        if (contador == 0)
        {
            throw new Error("A lista está vazia");
        }

        NoInt aux = first;

        for(int i = 0; i < pos; i++)
        {
            aux = aux.proximo;
        }
        return aux; //exibe o endereço do nó
    }

    public Object exibirBusca(int pos)
    {
        return buscar(pos).dado;
    }

    public int tamanho()
    {
        return contador;
    }

    public boolean empty()
    {
        return contador == 0;
    }

    public void exibir()
    {
        NoInt aux = first;

        while(aux != null)
        {
            System.out.println(aux.dado + " ");
            aux = aux.proximo;
        }
    }

}

class NoInt
{
    public int dado;
    public NoInt anterior;
    public NoInt proximo;

    public NoInt(int elemento)
    {
        dado = elemento;
        proximo = null;
        anterior = null;
    }
}

