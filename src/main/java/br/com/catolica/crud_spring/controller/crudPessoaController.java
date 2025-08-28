package br.com.catolica.crud_spring.controller;

import br.com.catolica.crud_spring.model.Pessoa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class crudController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }

    private static List<Pessoa> listaPessoas = new ArrayList<>();

    private int contador = 1;
    @PostMapping
    public Pessoa criaPessoa(@RequestBody Pessoa pessoa){
        pessoa.setId(contador++);
        listaPessoas.add(pessoa);
        return pessoa;
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return listaPessoas;
    }

    @GetMapping("/{id}")
    public Object buscarPorId(@PathVariable int id) {

        for (Pessoa p : listaPessoas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return "Pessoa com ID " + id + " não encontrada.";
    }

    @PutMapping("/{id}")
    public Pessoa atualizarPessoa(@PathVariable int id, @RequestBody Pessoa pessoaAtualizada) {
        for (Pessoa p : listaPessoas) {
            if (p.getId() == id) {
                p.setNome(pessoaAtualizada.getNome());
                p.setIdade(pessoaAtualizada.getIdade());
                return p;
            }
        }
        return null;
    }
}
