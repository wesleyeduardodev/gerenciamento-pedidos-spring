package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.RecursoNaoEncontradoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.UsuarioRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioConfig::new)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Email:" + email + " não enconstrado"));
    }
}