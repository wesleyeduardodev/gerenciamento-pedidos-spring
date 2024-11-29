/*
package br.com.aceleraprogramador.gerenciamento_pedidos.security;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.UsuarioAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.RoleType;
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
        Usuario usuario = UsuarioAdapter.criarPerfilDeUsuario("Usuario", passwordEncoder.encode("password"), "usuario@usuario.com", RoleType.ROLE_USUARIO);
        Usuario gerente = UsuarioAdapter.criarPerfilDeUsuario("Gerente", passwordEncoder.encode("password"), "gerente@gerente.com", RoleType.ROLE_GERENTE);
        Usuario administrador = UsuarioAdapter.criarPerfilDeUsuario("Administrador", passwordEncoder.encode("password"), "administrador@administrador.com", RoleType.ROLE_ADMINISTRADOR);
        usuarioRepository.saveAll(List.of(usuario, gerente, administrador));
    }
}*/
