package br.com.catolica.crud_spring.service;

import br.com.catolica.crud_spring.exceptions.RecursoNaoEncontradoException;
import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.model.Endereco;
import br.com.catolica.crud_spring.model.dto.request.ContatoRequestDTO;
import br.com.catolica.crud_spring.model.dto.response.ContatoResponseDTO;
import br.com.catolica.crud_spring.model.dto.response.EnderecoResponseDTO;
import br.com.catolica.crud_spring.repository.ContatoJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ContatoResponseDTO> listarContatos() {
        List<Contato> contatos = contatoRepository.findAll();

        if (contatos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum contato encontrado.");
        }

        List<ContatoResponseDTO> dtos = new ArrayList<>();

        for (Contato c : contatos) {
            EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO(
                    c.getEndereco().getRua(),
                    c.getEndereco().getNumero()
            );
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO(
                    c.getNome(),
                    c.getEmail(),
                    enderecoDTO
            );
            dtos.add(contatoDTO);
        }

        return dtos;
    }

    public Contato buscarPorId(int id) {
        return contatoRepository.findById(id);
    }

    public ContatoResponseDTO salvarContato(ContatoRequestDTO dto) {
        Contato contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());

        Endereco endereco = new Endereco();
        endereco.setRua(dto.getEndereco().getRua());
        endereco.setNumero(dto.getEndereco().getNumero());
        contato.setEndereco(endereco);

        Contato contatoSalvo = contatoRepository.save(contato);

        //Monta ContatoResponseDTO
        ContatoResponseDTO contatoResponse = new ContatoResponseDTO(contatoSalvo);
        return contatoResponse;
    }

    public int atualizarContato(int id, Contato contato) {
        return contatoRepository.update(id, contato);
    }

    public int removerContato(int id) {
        return contatoRepository.delete(id);
    }
}
