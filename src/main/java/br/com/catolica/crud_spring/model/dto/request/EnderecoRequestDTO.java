package br.com.catolica.crud_spring.model.dto.request;

public class EnderecoRequestDTO {

    private String rua;

    private String numero;

    public EnderecoRequestDTO() {
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
