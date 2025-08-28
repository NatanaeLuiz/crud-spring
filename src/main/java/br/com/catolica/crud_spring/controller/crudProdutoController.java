package com.catolica.crud_spring.controller;

import com.catolica.crud_spring.model.entity.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin(origins = "*") //Utilizado para evitar problema de crossOrigin na interface
@RestController
@RequestMapping("/produtos")
public class crudProdutoController {

    private static Produto[] produtos = new Produto[100]; // limite fixo
    private static int contadorId = 1;
    private static int total = 0;

    // CREATE
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        produto.setId(contadorId++);
        produtos[total++] = produto;
        return produto;
    }

    // READ - listar todos
    @GetMapping
    public Produto[] listarProdutos() {
        return Arrays.copyOf(produtos, total);
    }

    // READ - buscar por ID
    @GetMapping("/{id}")
    public Object buscarPorId(@PathVariable int id) {
        for (int i = 0; i < total; i++) {
            if (produtos[i].getId() == id) {
                return produtos[i];
            }
        }
        return "Produto com ID " + id + " não encontrado.";
    }

    // UPDATE
    @PutMapping("/{id}")
    public Object atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
        for (int i = 0; i < total; i++) {
            if (produtos[i].getId() == id) {
                produtos[i].setNome(produtoAtualizado.getNome());
                produtos[i].setPreco(produtoAtualizado.getPreco());
                return produtos[i];
            }
        }
        return "Produto não encontrado.";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String removerProduto(@PathVariable int id) {
        for (int i = 0; i < total; i++) {
            if (produtos[i].getId() == id) {
                // desloca elementos para a esquerda
                for (int j = i; j < total - 1; j++) {
                    produtos[j] = produtos[j + 1];
                }
                produtos[--total] = null;
                return "Produto removido com sucesso.";
            }
        }
        return "Produto não encontrado.";
    }
}
