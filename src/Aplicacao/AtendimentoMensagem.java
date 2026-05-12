/*

RM561545 Murilo Lopes Frade
RM564354 Rafael Ramos Cesario
RM556183 Eduardo Moreira

 */

package Aplicacao;

import Entidades.Mensagem;
import Fila.FilaMensagem;

import java.util.Scanner;

public class AtendimentoMensagem {

    static Scanner sc = new Scanner(System.in);
    static FilaMensagem filaReclamacao =new FilaMensagem();
    static FilaMensagem filaSugestao = new FilaMensagem();
    static FilaMensagem filaResolucao = new FilaMensagem();
    static Mensagem mensagem;

    public static void main(String[] args) {

        int opcao; // variavel para receber a opção escolhida pelo cliente

        do {
            System.out.println("=".repeat(60));
            System.out.println("==> SISTEMA DE ATENDIMENTO - CONVÊNIOS MÉDICOS");
            System.out.println("=".repeat(60));
            System.out.println("0- Encerra o programa");
            System.out.println("1- Recebimento de Mensagem");
            System.out.println("2- Atendimento de Mensagem");
            System.out.println("3- Recebimento e Encaminhamento de Resolução");
            System.out.println("-".repeat(60));
            System.out.print("Digite a opção escolhida:");

            opcao = sc.nextInt();

            switch (opcao){
                case 0:
                    if (filaReclamacao.isEmpty()|| filaSugestao.isEmpty()){
                        System.out.println("Programa encerrado com sucesso!");
                        return;
                    }else{
                        System.out.println("Contem mensagem a serem lidas.");
                        System.out.println("Precione enter para continuar.");
                        sc.nextLine();
                    }
                    break;
                case 1:
                    RecebimentoMensagem();
                    break;
                case 2:
                    atendimentoMensagem();
                    break;
                case 3:
                    mensagem = filaResolucao.dequeue();
                    if (mensagem != null){
                        System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida pelo setor responsável. Obrigado!!!");
                    } else {
                        System.out.println("Sem mensagem pendente");
                    }
                    break;
                default:
                    System.out.println("Opção Invalida ");
            }
        }while (true);

    }

    public static void RecebimentoMensagem(){

        String nome;
        int motivo; //1-Reclamação/2-Sugestão
        int formaCont; // 1-Whatsapp/2-Plicativo
        String contato; // Guarda ou o Email ou oTelefone
        String texto;// Recebe o texto da mensagem

        sc.nextLine();
        System.out.println("=> Recebimento de Mensagem");
        System.out.println("Informe o seu nome (opcional):");
        nome = sc.nextLine();

        while (true) {
            System.out.println("\nForma de contato:");
            System.out.println("1 - Whatsapp");
            System.out.println("2 - Aplicativo");
            formaCont = sc.nextInt();

            sc.nextLine();

            if (formaCont == 1) {
                System.out.println("Informe o seu telefone:");
                contato = sc.nextLine();
                break;
            } else if (formaCont == 2) {
                System.out.println("Informe o seu e-mail:");
                contato = sc.nextLine();
                break;
            } else {
                System.out.println("Forma de contato inválida!");
            }

        }

        while (true) {

            System.out.println("\nMotivo de contato:");
            System.out.println("1 - Reclamação");
            System.out.println("2 - Sugestão");
            motivo = sc.nextInt();

            sc.nextLine();

            if (motivo == 1) {
                System.out.println("Qual é a sua reclamação?");
                texto = sc.nextLine();
                mensagem = new Mensagem(nome, contato, motivo, texto);
                filaReclamacao.enqueue(mensagem);
                break;
            } else if (motivo == 2) {
                System.out.println("Qual é a sua sugestão?");
                texto = sc.nextLine();
                mensagem = new Mensagem(nome, contato, motivo, texto);
                filaSugestao.enqueue(mensagem);
                break;
            } else {
                System.out.println("Motivo inválido!");
            }
        }

    }

    public static void atendimentoMensagem(){

        System.out.println("\n==> Atendimento de Mensagem");

        if (filaReclamacao.isEmpty() && filaSugestao.isEmpty()){
            System.out.println("Nenhuma mensagem a ser repondida");
            return;
        }

        System.out.println("Opções:");
        if (!filaReclamacao.isEmpty()){
            System.out.println("1- Reclamação");
        }
        if (!filaSugestao.isEmpty()){
            System.out.println("2- Sugestão");
        }
        System.out.println("Digite sua opção:");
        int opcao = sc.nextInt();

        Mensagem atendida = (opcao == 1) ? filaReclamacao.dequeue() : filaSugestao.dequeue();

        if (atendida!=null){
            System.out.println("Mensagem:"+atendida.getTexto());
            System.out.println("""
                    1- Resolver agora
                    2- Encaminar
                    """);
            int decisao = sc.nextInt();

            if (decisao==1){
                System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida. Obrigado!!!");
            }else {
                System.out.println("Cliente avisado que em breve receberá resposta");
                filaResolucao.enqueue(atendida);
            }

        }

    }
}