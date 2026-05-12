package Fila;

import Entidades.Mensagem;

public class FilaMensagem {
    final int N = 10;
    Mensagem dados[] = new Mensagem[N];
    int ini, fim, cont;

    public void init(){
        ini = fim = cont = 0;
    }

    public boolean isEmpty(){
        //Verifica se está Vaziu
        return (cont == 0);
    }

    public boolean isFull(){
        //Verifica se está Cheio

        return (cont == N);
    }

    public void enqueue(Mensagem elem){
        // Adiciona coisas da fila
        if (isFull()){
            System.out.println("Fila cheia!");
        }
        else{
            dados[fim] = elem;
            cont++;
            fim = (fim+1) % N;
        }
    }

    public Mensagem dequeue(){
        // Remove coisas da fila
        Mensagem elem = dados[ini];
        cont--;
        ini = (ini+1) % N;
        return elem;
    }

}

