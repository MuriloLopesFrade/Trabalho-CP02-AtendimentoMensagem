package Entidades;

public class Mensagem {

    String nome;
    String contato; // Variavel para armazenar o meio Email ou telefone
    int motivo; // recebendo valor 1 para reclamação ou 2 para sugestão
    String texto;

    public Mensagem(String nome,String contato, int motivo,String texto) {
        this.nome = nome;
        this.contato = contato;
        this.motivo = motivo;
        this.texto = texto;
    }

    public String getIdentificador() {
        return contato;
    }

    public String getTexto() {
        return texto;
    }

    public int getMotivo() {
        return motivo;
    }

    public String getNome() {
        return nome;
    }

    public void setIdentificador(String contato) {
        this.contato = contato;
    }

    public void setMensagem(String texto) {
        this.texto = texto;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "Email/Telefone='" + contato + '\'' +
                ", nome='" + nome + '\'' +
                ", motivo=" + motivo +
                ", mensagem='" + texto + '\'' +
                '}';
    }
}
