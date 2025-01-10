package br.com.aceleraprogramador.gerenciamento_pedidos.controller.expense;

import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ExpenseRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ExpenseResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.CategoriaService;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gerenciamento de Gastos")
@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;


    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse response = expenseService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable Long id, @Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse response = expenseService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAll() {
        List<ExpenseResponse> responses = expenseService.findAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable Long id) {
        ExpenseResponse response = expenseService.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}