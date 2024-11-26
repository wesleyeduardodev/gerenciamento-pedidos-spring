package br.com.aceleraprogramador.gerenciamento_pedidos.security;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.ModelRole;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.Role;
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
        Usuario usuario = criarPerfilDeUsuario("Usuario", "password", "usuario@usuario.com", Role.ROLE_USUARIO);
        Usuario gerente = criarPerfilDeUsuario("Gerente", "password", "gerente@gerente.com", Role.ROLE_GERENTE);
        Usuario administrador = criarPerfilDeUsuario("Administrador", "password", "administrador@administrador.com", Role.ROLE_ADMINISTRADOR);
        usuarioRepository.saveAll(List.of(usuario, gerente, administrador));
    }

    private Usuario criarPerfilDeUsuario(String nome, String senha, String email, Role role) {
        return Usuario
                .builder()
                .nome(nome)
                .senha(passwordEncoder.encode(senha))
                .email(email)
                .roles(List.of(ModelRole.
                        builder()
                        .name(role)
                        .build()))
                .build();
    }
}