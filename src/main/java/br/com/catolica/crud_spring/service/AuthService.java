package br.com.catolica.crud_spring.service;

import br.com.catolica.crud_spring.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private Map<String, Usuario> usuarios = new HashMap<>();

    public AuthService() {
        // usuário fixo para teste: admin / 1234
        usuarios.put("admin", new Usuario("admin", "1234"));
    }

    public boolean autenticar(String username, String password) {
        Usuario user = usuarios.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
