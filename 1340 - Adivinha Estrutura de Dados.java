import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        Scanner s = new Scanner(System.in);
        
        FilaDinamica fila = new FilaDinamica();

        FilaPrioridadeLO filaPrioridade = new FilaPrioridadeLO();

        PilhaDinamica pilha = new PilhaDinamica();

        String l = s.nextLine();
        int n, x, y;

        while (!l.equals("") && s.hasNextLine()) //Início do caso de teste
        {
            n = Integer.parseInt(l);

            boolean queue = true;
            boolean stack = true;
            boolean priorityQueue = true;

            for(int i = 0; i < n; i++)
            {
                x = s.nextInt();
                y = s.nextInt();
                s.nextLine();

                if (x == 1)
                {
                    fila.enqueue(y);
                    pilha.push(y);
                    filaPrioridade.enqueue(y,y);
                }
                else
                {
                    if (fila.front() != y)
                    {
                        queue = false;
                    }
                    if (pilha.topo()!= y )
                    {
                        stack = false;
                    }
                    if (filaPrioridade.front() != y)
                    {
                        priorityQueue = false;
                    }

                    fila.dequeue();
                    pilha.pop();
                    filaPrioridade.remover();


                }
            }

            if (!priorityQueue && !queue && stack)
            {
                System.out.println("stack");
            }
            if (priorityQueue && !queue && !stack)
            {
                System.out.println("priority queue");
            }
            if (!priorityQueue && queue && !stack)
            {
                System.out.println("queue");
            }
            if (!priorityQueue && !queue && !stack)
            {
                System.out.println("impossible");
            }
            if ((queue && stack)||(priorityQueue && stack)||(priorityQueue && queue))
            {
                System.out.println("not sure");
            }

            pilha.limpar();
            fila.limpar();
            filaPrioridade.limpar();

            if(s.hasNextLine()) {
                l = s.nextLine();
            }
        }

    }

}

class FilaDinamica
{
    public NoInt first;
    public NoInt last;
    private int contador;

    public FilaDinamica()
    {
        first = null;
        last = null;
        contador = 0;

    }

    public void enqueue(int elemento)
    {
        NoInt novo = new NoInt (elemento);

        if (first == null)//lista vazia
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

    public void dequeue()
    {
        first = first.proximo;
        contador--;
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

    public int front()
    {
        return first.dado;
    }

    public NoInt buscar(int pos) //Terceira Operação Básica
    {
        //Caso da posição ser maior ou igual ao contador
        if(pos > contador)
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
class FilaPrioridadeLO
{
    private NoFilaP first;
    private NoFilaP last;
    private int contador;

    public FilaPrioridadeLO()
    {
        first = null;
        last = null;
        contador = 0;

    }

    public void enqueue(int elemento, int prioridade)
    {
        NoFilaP novo = new NoFilaP(elemento, prioridade);

        if (first == null)// Lista Vazia
        {
            first = novo;
            last = novo;
        }

        else if(novo.prioridade > first.prioridade) //Primeira posição
        {
            novo.proximo = first;
            first = novo;
        }

        else if (novo.prioridade <= last.prioridade) //Ultima posição
        {
            last.proximo = novo;
            last = novo;
        }
        else
        {
            NoFilaP aux = first;

            while(novo.prioridade <= aux.proximo.prioridade)
            {
                aux = aux.proximo;
            }

            novo.proximo = aux.proximo;
            aux.proximo = novo;

            aux.proximo.anterior = novo;
            novo.anterior = aux;
        }

        contador++;
    }


    public void remover()
    {
        first = first.proximo;
        contador--;
    }

    public void limpar()
    {
        contador = 0;
        first = null;
        last = null;
    }

    //Implementar de acordo com o contexto:
    /*
    public boolean contem(int elemento)
    {
        NoFilaP aux = first;

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

     */
    public int front()
    {
        return first.dado;
    }

    public NoFilaP buscar(int pos) //Terceira Operação Básica
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

        NoFilaP aux = first;

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
        NoFilaP aux = first;

        while(aux != null)
        {
            System.out.print(aux.dado + " ");
            aux = aux.proximo;
        }
        System.out.println();
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
        //Caso da posição ser maior q contador
        if(pos > contador)
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
            System.out.print(aux.dado + " ");
            aux = aux.proximo;

        }
        System.out.println();
    }

    public int topo() {
        return last.dado;
    }
}
class NoFilaP
{
    public int dado;
    public NoFilaP anterior;
    public NoFilaP proximo;
    public int prioridade;

    public NoFilaP(int elemento, int prioridade)
    {
        dado = elemento;
        proximo = null;
        anterior = null;
        this.prioridade = prioridade;
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
