package br.com.aceleraprogramador.gerenciamento_pedidos.security;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class InicializarUsuarios implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {

        Usuario manager = new Usuario();
        manager.setNome("Gerente");
        manager.setSenha(passwordEncoder.encode("password"));
        manager.setRoles("ROLE_GERENTE");
        manager.setEmail("gerente@gerente.com");

        Usuario admin = new Usuario();
        admin.setNome("Administrador");
        admin.setSenha(passwordEncoder.encode("password"));
        admin.setRoles("ROLE_ADMINISTRADOR");
        admin.setEmail("admin@admin.com");

        Usuario user = new Usuario();
        user.setNome("Usuario");
        user.setSenha(passwordEncoder.encode("password"));
        user.setRoles("ROLE_USUARIO");
        user.setEmail("usuario@usuario.com");

        usuarioRepository.saveAll(List.of(manager, admin, user));
    }
}