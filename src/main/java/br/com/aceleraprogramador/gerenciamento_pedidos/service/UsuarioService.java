package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.UsuarioAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.UsuarioRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.UsuarioResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.RoleType;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.RecursoNaoEncontradoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Role;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.RoleRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.UsuarioRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.PaginacaoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UsuarioResponse criarUsuario(UsuarioRequest request) {

        log.info("Criando um novo usuário...");
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

        Usuario usuario = UsuarioAdapter.toEntity(request, passwordEncoder.encode(request.getSenha()));

        List<RoleType> roleTypes = UsuarioAdapter.convertStringsToRoleTypes(request.getRoles());
        List<Role> roles = roleRepository.findAllByRoleTypeIn(roleTypes);
        usuario.setRoles(roles);

        usuarioRepository.save(usuario);
        UsuarioResponse usuarioResponse = UsuarioAdapter.toResponse(usuario);

        log.info("Usuário criado com sucesso...");

        return usuarioResponse;
    }

    public PageResponse<UsuarioResponse> buscarTodosOsUsuario(Integer pageNumber,
                                                              Integer pageSize,
                                                              String sortBy,
                                                              String sortDirection) {
        log.info("Buscando todos os usuários...");
        Pageable pageable = PaginacaoUtils.criarPageable(pageNumber, pageSize, sortBy, sortDirection);
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        Page<UsuarioResponse> responsePage = usuarios.map(UsuarioAdapter::toResponse);
        PageResponse<UsuarioResponse> pageResponse = PageResponse.
                <UsuarioResponse>builder()
                .content(responsePage.getContent())
                .currentPage(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .build();
        log.info("Usuários retornados com sucesso.");
        return pageResponse;
    }

    public void atualizarTodosOsDadosDoUsuario(Long id, UsuarioRequest request) {

        log.info("Atualizando todos os dados do usuário com ID: {}", id);
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

        Usuario usuarioPorId = buscarEntidadeUsuarioPorId(id);

        usuarioPorId.setNome(request.getNome());
        usuarioPorId.setEmail(request.getEmail());
        usuarioPorId.setRoles(UsuarioAdapter.toResponseListFromRequest(request.getRoles()));

        usuarioRepository.save(usuarioPorId);

        log.info("Usuário totalmente atualizado com sucesso.");
    }

    public UsuarioResponse buscarUsuarioPorId(Long id) {
        log.info("Buscando Usuário com ID:{}", id);
        Usuario usuario = buscarEntidadeUsuarioPorId(id);
        UsuarioResponse usuarioResponse = UsuarioAdapter.toResponse(usuario);
        log.info("Usuário retornado com sucesso.");
        return usuarioResponse;
    }

    public void removerUsuario(Long id) {
        log.info("Removendo do usuário com ID: {}", id);
        Usuario usuarioPorId = buscarEntidadeUsuarioPorId(id);
        usuarioRepository.delete(usuarioPorId);
        log.info("Usuário removido com sucesso.");
    }

    private Usuario buscarEntidadeUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            String erro = "Usuário não encontrado com o ID: " + id;
            throw new RecursoNaoEncontradoException(erro);
        } else {
            return usuarioOptional.get();
        }
    }
}