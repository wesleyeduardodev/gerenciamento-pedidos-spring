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
        Usuario usuario = criarPerfilUsuario();
        Usuario gerente = criarPerfilGerente();
        Usuario administrador = criarPerfilAdministrador();
        List<Usuario> usuarios = List.of(usuario, gerente, administrador);
        usuarioRepository.saveAll(usuarios);
    }

    private Usuario criarPerfilAdministrador() {
        Usuario administrador = new Usuario();
        administrador.setNome("Administrador");
        administrador.setSenha(passwordEncoder.encode("password"));
        ModelRole roleAdministrador = ModelRole.
                builder()
                .name(Role.ROLE_ADMINISTRADOR)
                .build();
        administrador.setRoles(List.of(roleAdministrador));
        administrador.setEmail("administrador@administrador.com");
        return administrador;
    }

    private Usuario criarPerfilGerente() {
        Usuario gerente = new Usuario();
        gerente.setNome("Gerente");
        gerente.setSenha(passwordEncoder.encode("password"));
        ModelRole roleGerente = ModelRole.
                builder()
                .name(Role.ROLE_GERENTE)
                .build();
        gerente.setRoles(List.of(roleGerente));
        gerente.setEmail("gerente@gerente.com");
        return gerente;
    }

    private Usuario criarPerfilUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setSenha(passwordEncoder.encode("password"));
        ModelRole roleUsuario = ModelRole.
                builder()
                .name(Role.ROLE_USUARIO)
                .build();
        usuario.setRoles(List.of(roleUsuario));
        usuario.setEmail("usuario@usuario.com");
        return usuario;
    }
}