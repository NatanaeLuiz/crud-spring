package br.com.catolica.crud_spring.model.dto.response;

import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.model.Endereco;

public class ContatoResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private EnderecoResponseDTO endereco;

    public ContatoResponseDTO(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.email = contato.getEmail();
        this.endereco = new EnderecoResponseDTO(contato.getEndereco());
    }

    public ContatoResponseDTO(String nome, String email, EnderecoResponseDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }
}
