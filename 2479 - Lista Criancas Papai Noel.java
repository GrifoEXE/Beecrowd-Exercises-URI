import java.io.IOException;
import java.util.Scanner;

/**
 * IMPORTANT:
 *      O nome da classe deve ser "Main" para que a sua solução execute
 *      Class name must be "Main" for your solution to execute
 *      El nombre de la clase debe ser "Main" para que su solución ejecutar
 */
public class Main {

    public static void main(String[] args) throws IOException
    {
        Scanner s = new Scanner(System.in);

        ListaOrdenadaAlfabetica lista = new ListaOrdenadaAlfabetica(); //Inicialização da lista

        int n = Integer.parseInt(s.nextLine()); //Quantidade de crianças

        int bom = 0, mal = 0; //Quantidade de crianças boas e más

        for(int i = 0; i < n; i++)
        {
            String name = s.nextLine();
            int size = name.length();
            String moral = name.substring(0, size - (size -1));
            name = name.substring(2, size);

            lista.add(name);

            if(moral.equals("+"))
            {
                bom++;
            }
            else if(moral.equals("-"))
            {
                mal++;
            }
        }

        lista.exibir();

        System.out.printf("Se comportaram: %d | Nao se comportaram: %d\n",bom,mal);
    }

}

class ListaOrdenadaAlfabetica //Duplamente Encadeada e Ordenada
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
        NoString aux = new NoString(elemento);
        if (first == null)//lista vazia
        {
            first = novo;
            last = novo;
            novo.proximo = null;
            novo.anterior = null;
        }
        else
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
        }
        contador++;
    }


    public void remover()
    {
        NoString aux = first;

        while(aux.proximo != last) //QUANDO PROX DO AUX FOR IGUAL AO ULTIMO
        {
            aux = aux.proximo;
        }

        aux.proximo = null;
        last = aux;
        contador--;

    }

    public void remover(String elemento)
    {
        NoString aux;
        int achou = 0;
        if (first == null)
        {
            System.out.println("Lista vazia");
        }

        else
        {
            aux = first;
            while(aux != null)
            {
                if(aux.dado.equals(elemento)) // aux.dado é igual à string elemento
                {
                    achou = achou + 1;
                    if(aux == first)
                    {
                        first = aux.proximo;
                        if( first != null)
                        {
                            first.anterior = null;
                        }
                        aux = first;
                    }
                    else if ( aux == last)
                    {
                        last = last.anterior;
                        last.proximo = null;
                        aux = null;
                    }
                    else
                    {
                        aux.anterior.proximo = aux.proximo;
                        aux.proximo.anterior = aux.anterior;
                        aux = aux.proximo;
                    }
                }
                else
                {
                    aux = aux.proximo;
                }
            }
        }
    }

    public void limpar()
    {
        contador = 0;
        first = null;
        last = null;
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
            System.out.println(aux.dado);
            aux = aux.proximo;
        }
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
