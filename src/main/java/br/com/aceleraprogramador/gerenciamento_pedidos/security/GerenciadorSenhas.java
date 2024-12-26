package br.com.aceleraprogramador.gerenciamento_pedidos.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GerenciadorSenhas {

    public static void main(String[] args) {
        String rawPassword = "123456";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Senha codificada: " + encodedPassword);
    }
}
