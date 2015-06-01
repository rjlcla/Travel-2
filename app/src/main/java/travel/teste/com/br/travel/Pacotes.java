package travel.teste.com.br.travel;

import java.io.Serializable;

public class Pacotes implements Serializable {

    int id;
    String descricao_pacote;
    String titulo_pacote;
    String valor_original;
    String valor_desconto;
    String foto;
    int qtdvendida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getqtdvendida() {
        return qtdvendida;
    }

    public void setqtdvendida(int id) {
        this.qtdvendida = qtdvendida;
    }

    public String getdescricao_pacote() {
        return descricao_pacote;
    }

    public void setdescricao_pacote(String url) {
        this.descricao_pacote = url;
    }

    public String gettitulo_pacote() {
        return titulo_pacote;
    }

    public void settitulo_pacote(String title) {
        this.titulo_pacote = title;
    }

    public String getvalor_original() {
        return valor_original;
    }

    public void setvalor_original(String date) {
        this.valor_original = date;
    }

    public String getvalor_desconto() {
        return valor_desconto;
    }

    public void setvalor_desconto(String author) {
        this.valor_desconto = author;
    }

    public String getfoto() {
        return foto;
    }

    public void setfoto(String foto) {
        this.foto = foto;
    }
}
