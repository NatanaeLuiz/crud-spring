package br.com.catolica.crud_spring.repository;

import br.com.catolica.crud_spring.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ContatoJdbcDAO {

    /* Comandos DML (Linguagem de Manipulação de Dados)
     *
    * */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Inserir contato
    public void salvar(Contato contato) {
        String sql = "INSERT INTO contatos (nome, email, endereco) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contato.getNome(), contato.getEmail(), contato.getEndereco());
    }

    // Listar todos os contatos
    public List<Contato> listarTodos() {
        String sql = "SELECT * FROM contatos";
        return jdbcTemplate.query(sql, rs -> {
            List<Contato> contatos = new ArrayList<>();
            while (rs.next()) { // percorre linha por linha
                Contato contato = new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );
                contatos.add(contato);
            }
            return contatos; // devolve a lista de contatos
        });
    }

    public Contato buscarPorId(int id) {
        String sql = "SELECT * FROM contatos WHERE id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, id), rs -> { //ps seta o valor do parâmetro no PreparedStatement, rs processa o ResultSet retornado
            if (rs.next()) { // percorre linha por linha
                return new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );
            }
            return null; // ou lançar uma exceção caso não encontre
        });
    }

    public void atualizar(int id, Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, email = ?, endereco = ? WHERE id = ?";
        jdbcTemplate.update(sql, contato.getNome(), contato.getEmail(), contato.getEndereco(), id);
    }

    public void remover(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
