package br.com.aceleraprogramador.gerenciamento_pedidos.service;

import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ExpenseRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ExpenseResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Categoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Expense;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.CategoriaRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoriaRepository categoriaRepository;


    public ExpenseResponse create(ExpenseRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + request.getCategoriaId()));

        Expense expense = new Expense();
        expense.setValor(request.getValor());
        expense.setCategoria(categoria);
        expense.setDataHora(request.getDataHora());

        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getValor(),
                savedExpense.getCategoria().getNome(),
                savedExpense.getDataHora()
        );
    }

    public ExpenseResponse update(Long id, ExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense não encontrada com ID: " + id));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + request.getCategoriaId()));

        expense.setValor(request.getValor());
        expense.setCategoria(categoria);
        expense.setDataHora(request.getDataHora());

        Expense updatedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                updatedExpense.getId(),
                updatedExpense.getValor(),
                updatedExpense.getCategoria().getNome(),
                updatedExpense.getDataHora()
        );
    }

    public List<ExpenseResponse> findAll() {
        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map(expense -> new ExpenseResponse(
                        expense.getId(),
                        expense.getValor(),
                        expense.getCategoria().getNome(),
                        expense.getDataHora()
                ))
                .collect(Collectors.toList());
    }

    public ExpenseResponse findById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense não encontrada com ID: " + id));

        return new ExpenseResponse(
                expense.getId(),
                expense.getValor(),
                expense.getCategoria().getNome(),
                expense.getDataHora()
        );
    }

    public void delete(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense não encontrada com ID: " + id);
        }
        expenseRepository.deleteById(id);
    }
}