import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);
        int i = 0;
        ListaDinamica listaYesOrdem = new ListaDinamica();
        String biggerNameYes = "";

        ListaOrdenadaAlfabetica listaYES = new ListaOrdenadaAlfabetica();
        ListaOrdenadaAlfabetica listaNO = new ListaOrdenadaAlfabetica();

        //for (int i = 0; i < 2147483647; i++)
        while(true)
        {
            String nameAndVote = s.nextLine();

            if (nameAndVote.endsWith("S"))
            {
                String name = nameAndVote.substring(0, nameAndVote.length() - 4);
                //listaYesOrdem.add(name);
                listaYES.add(name);
                if (name.length() > biggerNameYes.length()) // if que testa se o nome atual do yes é maior que o maior nome anterior.
                {
                    biggerNameYes = name; // recebe o maior nome atual.
                }
            }
            else if(nameAndVote.endsWith("O"))
            {
                String name = nameAndVote.substring(0, nameAndVote.length() - 3);
                listaNO.add(name);
            }
            else if(nameAndVote.equals("FIM"))
            {
                break;
            }
        }


            listaYES.exibir();
            listaNO.exibir();
            System.out.print("\n");
            System.out.println("Amigo do Habay:");
            System.out.println(biggerNameYes);
            //System.out.println(sorteio(listaYesOrdem).dado);

    }

    public static NoString sorteio(ListaDinamica listaYesOrdem)
    {
        NoString winner = listaYesOrdem.first;

        for(int i = 0; i < listaYesOrdem.tamanho(); i++)
        {
            if(winner== null)
            {
                break;
            }
            else if(winner.dado.length() < winner.proximo.dado.length())
            {
                winner = winner.proximo;
            }

        }

        return winner;
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
            System.out.println(aux.dado);
            aux = aux.proximo;
        }
    }

}

class ListaDinamica //Duplamente Encadeada
{
    public NoString first;
    public NoString last;
    private int contador;

    public ListaDinamica()
    {
        first = null;
        last = null;
        contador = 0;

    }

    public void add(String elemento)
    {
        NoString novo = new NoString(elemento);

        if (first == null)//lista vazia
        {
            first = novo;
            last = novo;
            novo.proximo = null;
            novo.anterior = null;
            contador++;
        }
        else
        {
            if (!contem(novo.dado))//PROBLEMA TALVEZ ESTEJA NESTA CONDIÇÃO
            {
                last.proximo = novo;
                novo.anterior = last;
                novo.proximo = null;
                last = novo;
                contador++;
            }
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
            System.out.println(aux.dado + " ");
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
