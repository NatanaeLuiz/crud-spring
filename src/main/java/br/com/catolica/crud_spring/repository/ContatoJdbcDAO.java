package br.com.catolica.crud_spring.repository;

import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ContatoJdbcDAO {

    /* Comandos DML (Linguagem de Manipulação de Dados)
     * INSERT
     * SELECT
     * UPDATE
     * DELETE
    * */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Listar contato
    public List<Contato> findAll() {
        String sql = """
            SELECT c.id as c_id, c.nome, c.email,
                   e.id as e_id, e.rua, e.numero
            FROM contato c
            JOIN endereco e ON c.endereco_id = e.id
        """;

        return jdbcTemplate.query(sql, new ContatoComEnderecoMapper());
    }

    // BUSCAR POR ID
    public Contato findById(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c.id as c_id, c.nome, c.email, ")
            .append("e.id as e_id, e.rua, e.numero ")
            .append("FROM contato c ")
            .append("JOIN endereco e ON c.endereco_id = e.id ")
            .append("WHERE c.id = ?");

        String sql = sb.toString();

        return jdbcTemplate.queryForObject(sql, new ContatoComEnderecoMapper(), id);
    }

    // SALVAR
    public int save(Contato contato) {
        // 1. Inserir endereço
        String sqlEndereco = "INSERT INTO endereco (rua, numero) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contato.getEndereco().getRua());
            ps.setString(2, contato.getEndereco().getNumero());
            return ps;
        }, keyHolder);
        int enderecoId = keyHolder.getKey().intValue();

        // 2. Inserir contato
        String sqlContato = "INSERT INTO contato (nome, email, endereco_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sqlContato, contato.getNome(), contato.getEmail(), enderecoId);
    }

    // ATUALIZAR
    public int update(int id, Contato contato) {
        // Atualiza o endereço
        String sqlEndereco = "UPDATE endereco SET rua = ?, numero = ? WHERE id = ?";
        jdbcTemplate.update(sqlEndereco,
            contato.getEndereco().getRua(),
            contato.getEndereco().getNumero(),
            contato.getEndereco().getId());

        // Atualiza o contato
        String sqlContato = "UPDATE contato SET nome = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sqlContato,
                contato.getNome(),
                contato.getEmail(),
                id);
    }

    // REMOVER
    public int delete(int id) {
        // 1. Pegar endereco_id do contato
        Integer enderecoId = jdbcTemplate.queryForObject(
                "SELECT endereco_id FROM contato WHERE id = ?", Integer.class, id);

        // 2. Deletar contato
        jdbcTemplate.update("DELETE FROM contato WHERE id = ?", id);

        // 3. Deletar endereço
        return jdbcTemplate.update("DELETE FROM endereco WHERE id = ?", enderecoId);
    }

    // Mapper customizado para montar Contato + Endereco
    private static class ContatoComEnderecoMapper implements RowMapper<Contato> {
        @Override
        public Contato mapRow(ResultSet rs, int rowNum) throws SQLException {
            Endereco endereco = new Endereco();
            endereco.setId(rs.getInt("e_id"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getString("numero"));

            Contato contato = new Contato();
            contato.setId(rs.getInt("c_id"));
            contato.setNome(rs.getString("nome"));
            contato.setEmail(rs.getString("email"));
            contato.setEndereco(endereco);

            return contato;
        }
    }
}
