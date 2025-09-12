package br.com.catolica.crud_spring.model.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ContatoRequestDTO {
    private String nome;
    private String email;
    private EnderecoRequestDTO endereco;

    public ContatoRequestDTO(String nome, String email, EnderecoRequestDTO enderecorequestDTO) {
        this.nome = nome;
        this.email = email;
        this.endereco = enderecorequestDTO;
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

    public EnderecoRequestDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequestDTO endereco) {
        this.endereco = endereco;
    }
}
