package br.com.catolica.crud_spring.service;

import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.repository.ContatoJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {


    @Autowired
    private ContatoJdbcDAO contatoRepository;

//    Exemplo para injetar dependencia
//
//    private final ContatoJdbcDAO contatoRepository;
//
//    public ContatoService(ContatoJdbcDAO contatoRepository) {
//        this.contatoRepository = contatoRepository;
//    }

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    public Contato buscarPorId(int id) {
        return contatoRepository.findById(id);
    }

    public int salvarContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    public int atualizarContato(int id, Contato contato) {
        return contatoRepository.update(id, contato);
    }

    public int removerContato(int id) {
        return contatoRepository.delete(id);
    }
}
