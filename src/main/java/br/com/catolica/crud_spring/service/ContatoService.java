package br.com.catolica.crud_spring.service;

import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.repository.ContatoJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {


    @Autowired
    private ContatoJdbcDAO contatoDAO;

    public void atualizarContato(int id, Contato contato) {
        contatoDAO.atualizar(id, contato);
    }
}
