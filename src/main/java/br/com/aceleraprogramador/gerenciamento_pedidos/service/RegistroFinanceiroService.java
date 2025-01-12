package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.RegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.RegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoTransacaoFinanceira;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.RegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.CategoriaRegistroFinanceiroRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.RegistroFinanceiroRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class RegistroFinanceiroService {

    private final RegistroFinanceiroRepository registroFinanceiroRepository;
    private final CategoriaRegistroFinanceiroRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Transactional
    public RegistroFinanceiroResponse createRegistro(RegistroFinanceiroRequest request) {
        CategoriaRegistroFinanceiro categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        RegistroFinanceiro registro = RegistroFinanceiro.builder()
                .tipoRegistro(TipoRegistroFinanceiro.valueOfCodigo(request.getTipoRegistro()))
                .tipoTransacao(TipoTransacaoFinanceira.valueOfCodigo(request.getTipoTransacao()))
                .categoria(categoria)
                .valor(request.getValor())
                .dataTransacao(LocalDateTime.parse(request.getDataTransacao(), DATE_TIME_FORMATTER))
                .usuario(usuario)
                .build();

        RegistroFinanceiro savedRegistro = registroFinanceiroRepository.save(registro);

        return toResponse(savedRegistro);
    }

    @Transactional(readOnly = true)
    public List<RegistroFinanceiroResponse> getAllRegistros() {
        return registroFinanceiroRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroFinanceiroResponse getRegistroById(Long id) {
        RegistroFinanceiro registro = registroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro financeiro não encontrado."));
        return toResponse(registro);
    }

    @Transactional
    public RegistroFinanceiroResponse updateRegistro(Long id, RegistroFinanceiroRequest request) {
        RegistroFinanceiro registro = registroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro financeiro não encontrado."));
        CategoriaRegistroFinanceiro categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        registro.setTipoRegistro(TipoRegistroFinanceiro.valueOfCodigo(request.getTipoRegistro()));
        registro.setTipoTransacao(TipoTransacaoFinanceira.valueOfCodigo(request.getTipoTransacao()));
        registro.setCategoria(categoria);
        registro.setValor(request.getValor());
        registro.setDataTransacao(LocalDateTime.parse(request.getDataTransacao(), DATE_TIME_FORMATTER));
        registro.setUsuario(usuario);

        RegistroFinanceiro updatedRegistro = registroFinanceiroRepository.save(registro);

        return toResponse(updatedRegistro);
    }

    @Transactional
    public void deleteRegistro(Long id) {
        if (!registroFinanceiroRepository.existsById(id)) {
            throw new EntityNotFoundException("Registro financeiro não encontrado.");
        }
        registroFinanceiroRepository.deleteById(id);
    }

    private RegistroFinanceiroResponse toResponse(RegistroFinanceiro registro) {
        return RegistroFinanceiroResponse.builder()
                .id(registro.getId())
                .tipoRegistro(registro.getTipoRegistro().getCodigo())
                .tipoTransacao(registro.getTipoTransacao().getCodigo())
                .idCategoria(registro.getCategoria().getId())
                .valor(registro.getValor())
                .dataTransacao(registro.getDataTransacao().format(DATE_TIME_FORMATTER))
                .idUsuario(registro.getUsuario().getId())
                .dataCriacao(registro.getDataCriacao().format(DATE_TIME_FORMATTER))
                .dataAlteracao(registro.getDataAlteracao() != null
                        ? registro.getDataAlteracao().format(DATE_TIME_FORMATTER)
                        : null)
                .build();
    }
}