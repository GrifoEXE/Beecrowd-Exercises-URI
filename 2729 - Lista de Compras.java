import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();

        for(int i = 0; i < n; i++)
        {
            ListaOrdenadaAlfabetica lista = new ListaOrdenadaAlfabetica();

            String[] aux = s.nextLine().split(" ");

            for (String x : aux)
            {
                if (!lista.contem(x))
                {
                    lista.add(x);
                }
            }
            lista.exibir();
            lista.limpar();
        }
    }
}


class ListaOrdenadaAlfabetica //Duplamente Encadeada e Ordenada e exclui itens duplicados
{
    private NoString first;
    private NoString last;
    private int contador;

    public ListaOrdenadaAlfabetica()
    {
        first = null;
        last = null;
        contador = 0;

    }

    public void add(String elemento)
    {
        NoString novo = new NoString(elemento);
        NoString aux;
        if (first == null)//lista vazia
        {
            first = novo;
            last = novo;
            novo.proximo = null;
            novo.anterior = null;
            contador++;
        }
        else if (!contem(novo.dado))
        {
            aux = first;
            while (aux != null && novo.dado.compareTo(aux.dado) > 0)
            {
                aux = aux.proximo;
            }
            if(aux == first)
            {
                novo.proximo = first;
                novo.anterior = null;
                first.anterior = novo;
                first = novo;
            }
            else if (aux == null)
            {
                last.proximo = novo;
                novo.anterior = last;
                last = novo;
                last.proximo = null;
            }
            else
            {
                novo.proximo = aux;
                aux.anterior.proximo = novo;
                novo.anterior = aux.anterior;
                aux.anterior = novo;
            }
            contador++;
        }
    }

    public boolean contem(String elemento)
    {
        NoString aux = first;
        while(aux != null)
        {
            if (aux.dado.equals(elemento))
            {
                return true;
            }
            aux = aux.proximo;
        }
        return false;
    }

    public NoString buscar(int pos) //Terceira Operação Básica
    {
        //Precisa tratar o caso da posição ser maior ou igual ao contador
        if (pos >= contador)
        {
            throw new Error("A posição está fora da lista");
        }

        //Precisa tratar o caso da lista estar vazia
        if(contador == 0)
        {
            throw new Error("A lista está vazia");
        }

        NoString aux = first;

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
        return contador + 1;
    }

    public void exibir()
    {
        NoString aux = first;

        while(aux != null)
        {
            if(aux == last)
            {
                System.out.println(aux.dado);
            }
            else
            {
                System.out.print(aux.dado + " ");
            }
            aux = aux.proximo;
        }
    }
    public void limpar()
    {
        contador = 0;
        first = null;
        last = null;
    }


}


class NoString
{
    public String dado;
    public NoString anterior;
    public NoString proximo;

    public NoString(String elemento)
    {
        dado = elemento;
        proximo = null;
        anterior = null;
    }
}
