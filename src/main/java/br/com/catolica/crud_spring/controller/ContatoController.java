package br.com.catolica.crud_spring.controller;


import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.model.Pessoa;
import br.com.catolica.crud_spring.model.dto.request.ContatoRequestDTO;
import br.com.catolica.crud_spring.model.dto.response.ContatoResponseDTO;
import br.com.catolica.crud_spring.repository.ContatoJdbcDAO;
import br.com.catolica.crud_spring.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

//    @RequestMapping("/all")
//    public List<Contato> listaContatos() {
//        List<Contato> lista = new ArrayList<>();
//        Contato c = new Contato(1, "João", "joao@gmail.com", "Rua J 1");
//        lista.add(c);
//        c = new Contato(2, "Maria", "maria@gmail.com", "Rua M 2");
//        lista.add(c);
//        c = new Contato(3, "José", "jose@gmail.com", "Rua J 3");
//        lista.add(c);
//        c = new Contato(4, "Natanael", "natanael@gmail.com", "Rua N 4");
//        lista.add(c);
//        return lista;
//    }

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> listar() {
        List<ContatoResponseDTO> response = service.listarContatos();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> salvar(@RequestBody ContatoRequestDTO contatoDto) {
        ContatoResponseDTO contatoResponse = service.salvarContato(contatoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoResponse);
    }

    @GetMapping("/{id}")
    public Contato buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizarPessoa(@PathVariable int id, @RequestBody Contato contato) {
        service.atualizarContato(id, contato);
//        if (contatoAtualizado != null) {
//            return ResponseEntity.ok(contatoAtualizado); // HTTP 200 com o contato atualizado
//        } else {
//            return ResponseEntity.notFound().build(); // HTTP 404 se não encontrou
//        }
    }

    @DeleteMapping("/{id}")
    public void removerContato(@PathVariable int id) {
        service.removerContato(id);
    }


}
