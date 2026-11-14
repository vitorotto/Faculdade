package model;

public class Contato {
    private Integer contatoId;
    private String nome;
    private String eMail;
    private String telefone;


    public Contato(String nome, String eMail, String telefone){
        this.nome = nome;
        this.eMail = eMail;
        this.telefone = telefone;
    }

    public Contato(){

    }

    public Integer getContatoId() {
        return contatoId;
    }

    public void setContatoId(Integer contatoId) {
        this.contatoId = contatoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "ID: "+contatoId+" Nome: "+nome+" E-mail: "+eMail+" Telefone: "+telefone;
    }
}
