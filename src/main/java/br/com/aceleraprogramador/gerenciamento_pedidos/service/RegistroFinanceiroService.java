package br.com.aceleraprogramador.gerenciamento_pedidos.service;

import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.RegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.RegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoTransacaoFinanceira;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.RegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.SubCategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.CategoriaRegistroFinanceiroRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.RegistroFinanceiroRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.SubCategoriaRegistroFinanceiroRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateTimeUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistroFinanceiroService {

    private final RegistroFinanceiroRepository registroFinanceiroRepository;
    private final CategoriaRegistroFinanceiroService categoriaRegistroFinanceiroService;
    private final SubCategoriaRegistroFinanceiroService subCategoriaRegistroFinanceiroService;

    @Transactional
    public RegistroFinanceiroResponse createRegistro(Long idUsuario, RegistroFinanceiroRequest request) {

        CategoriaRegistroFinanceiro categoriaRegistroFinanceiro = categoriaRegistroFinanceiroService.findByIdAndReturnCategoriaRegistroFinanceiro(request.getIdCategoria(), idUsuario);

        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = null;
        if (Objects.nonNull(request.getIdSubCategoria())) {
            subCategoriaRegistroFinanceiro = subCategoriaRegistroFinanceiroService.findByIdUsuario(request.getIdSubCategoria(), idUsuario);
        }

        RegistroFinanceiro registro = RegistroFinanceiro.builder()
                .tipoRegistro(TipoRegistroFinanceiro.valueOfCodigo(request.getTipoRegistro()))
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .tipoTransacao(TipoTransacaoFinanceira.valueOfCodigo(request.getTipoTransacao()))
                .categoria(categoriaRegistroFinanceiro)
                .subCategoria(subCategoriaRegistroFinanceiro)
                .valor(request.getValor())
                .dataTransacao(DateTimeUtil.fromIsoString(request.getDataTransacao()))
                .usuario(Usuario.builder().id(idUsuario).build())
                .build();

        RegistroFinanceiro savedRegistro = registroFinanceiroRepository.save(registro);

        return toResponse(savedRegistro);
    }

    @Transactional(readOnly = true)
    public List<RegistroFinanceiroResponse> getAllRegistros(Long idUsuario) {
        List<RegistroFinanceiro> allByUsuarioId = registroFinanceiroRepository.findAllByUsuarioId(idUsuario);
        return allByUsuarioId.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroFinanceiroResponse getRegistroById(Long id, Long idUsuario) {
        RegistroFinanceiro registro = registroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .filter(r -> r.getUsuario().getId().equals(idUsuario))
                .orElseThrow(() -> new EntityNotFoundException("Registro financeiro não encontrado para este usuário."));
        return toResponse(registro);
    }

    @Transactional
    public RegistroFinanceiroResponse updateRegistro(Long id, Long idUsuario, RegistroFinanceiroRequest request) {

        RegistroFinanceiro registro = registroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .filter(r -> r.getUsuario().getId().equals(idUsuario))
                .orElseThrow(() -> new EntityNotFoundException("Registro financeiro não encontrado para este usuário."));

        CategoriaRegistroFinanceiro categoriaRegistroFinanceiro = categoriaRegistroFinanceiroService.findByIdAndReturnCategoriaRegistroFinanceiro(request.getIdCategoria(), idUsuario);

        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = null;
        if (Objects.nonNull(request.getIdSubCategoria())) {
            subCategoriaRegistroFinanceiro = subCategoriaRegistroFinanceiroService.findByIdUsuario(request.getIdSubCategoria(), idUsuario);
        }

        registro.setTitulo(request.getTitulo());
        registro.setDescricao(request.getDescricao());
        registro.setTipoRegistro(TipoRegistroFinanceiro.valueOfCodigo(request.getTipoRegistro()));
        registro.setTipoTransacao(TipoTransacaoFinanceira.valueOfCodigo(request.getTipoTransacao()));
        registro.setCategoria(categoriaRegistroFinanceiro);
        registro.setSubCategoria(subCategoriaRegistroFinanceiro);
        registro.setValor(request.getValor());
        registro.setDataTransacao(DateTimeUtil.fromIsoString(request.getDataTransacao()));
        registro.setUsuario(Usuario.builder().id(idUsuario).build());

        RegistroFinanceiro updatedRegistro = registroFinanceiroRepository.save(registro);

        return toResponse(updatedRegistro);
    }

    @Transactional
    public void deleteRegistro(Long id, Long idUsuario) {
        if (!registroFinanceiroRepository.existsByIdAndUsuarioId(id, idUsuario)) {
            throw new EntityNotFoundException("Registro financeiro não encontrado para este usuário.");
        }
        registroFinanceiroRepository.deleteById(id);
    }

    private RegistroFinanceiroResponse toResponse(RegistroFinanceiro registro) {
        Long idSubCategoria = null;
        String nomeSubCategoria = null;
        if(Objects.nonNull(registro.getSubCategoria())) {
            idSubCategoria = registro.getSubCategoria().getId();
            nomeSubCategoria = registro.getSubCategoria().getNome();
        }
        return RegistroFinanceiroResponse.builder()
                .id(registro.getId())
                .titulo(registro.getTitulo())
                .descricao(registro.getDescricao())
                .tipoRegistro(registro.getTipoRegistro().getCodigo())
                .tipoTransacao(registro.getTipoTransacao().getCodigo())
                .idCategoria(registro.getCategoria().getId())
                .nomeCategoria(registro.getCategoria().getNome())
                .idSubCategoria(idSubCategoria)
                .nomeSubCategoria(nomeSubCategoria)
                .valor(registro.getValor())
                .dataTransacao(DateTimeUtil.toIsoString(registro.getDataTransacao()))
                .build();
    }
}
