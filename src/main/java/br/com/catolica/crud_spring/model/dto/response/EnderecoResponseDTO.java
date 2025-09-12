package br.com.catolica.crud_spring.model.dto.response;


import br.com.catolica.crud_spring.model.Endereco;

public class EnderecoResponseDTO {

    private Integer id;
    private String rua;
    private String numero;

    public EnderecoResponseDTO(String rua, String numero) {
        this.rua = rua;
        this.numero = numero;
    }

    public EnderecoResponseDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
