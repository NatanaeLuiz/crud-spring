package br.com.catolica.crud_spring.controller;

import br.com.catolica.crud_spring.model.Pessoa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class crudPessoaController {

    //Verbos HTTP
    /*
    GET - Buscar
    POST - Criar
    PUT - Atualizar
    DELETE - Remover
     */

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }

    //Sem usar banco de dados, apenas com lista em memoria
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

    @DeleteMapping("/{id}")
    public String removerPessoa(@PathVariable int id) {

        for (Pessoa p : listaPessoas) {
            if (p.getId() == id) {
                listaPessoas.remove(p);
                return "Pessoa com ID " + id + " removida com sucesso.";
            }
        }
        return "Pessoa com ID " + id + " não encontrada.";

        //boolean removido = listaPessoas.removeIf(p -> p.getId() == id);
        //return removido ? "Pessoa removida com sucesso." : "Pessoa não encontrada.";
    }
}
