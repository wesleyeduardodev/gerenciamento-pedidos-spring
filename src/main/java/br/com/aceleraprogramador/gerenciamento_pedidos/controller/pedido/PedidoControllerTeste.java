package br.com.aceleraprogramador.gerenciamento_pedidos.controller.pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.PedidoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gerenciamento de Pedidos Teste")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PedidoControllerTeste  {


    @GetMapping
    public void criarPedido() {
        System.out.printf("chegou aki");
    }

}
