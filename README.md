# Projeto Cliente API

Este projeto é uma API para gerenciar dados de clientes. Ele fornece funcionalidades para realizar buscas com filtros personalizados, usando JPQL para consultas dinâmicas. O objetivo é facilitar a busca de clientes usando múltiplos critérios de forma opcional.

## Tecnologias Utilizadas

- **Java 17+**: Linguagem de programação principal.
- **Spring Boot**: Framework utilizado para simplificar a criação de APIs REST.
- **Spring Data JPA**: Para interagir com o banco de dados usando ORM.
- **H2 Database**: Banco de dados em memória para testes.
- **Maven**: Ferramenta de automação e gerenciamento de dependências.

## Estrutura do Projeto

O projeto possui as seguintes camadas principais:

1. **Camada de Repositório** (`ClienteRepository`):
   - Utiliza o `JpaRepository` para acesso aos dados.
   - Inclui um método customizado com uma consulta JPQL para buscar clientes com base em critérios opcionais.

2. **Camada de Serviço** (`ClienteService`):
   - Responsável pela lógica de negócios.
   - Utiliza o repositório para realizar operações no banco de dados.

## Funcionalidades

- **Busca de Clientes**: O projeto permite buscar clientes por diferentes atributos, todos de forma opcional, como `id`, `nome`, `email`, `telefone`, `endereço`, e `profissão`. A busca utiliza a cláusula `LIKE` para os atributos do tipo `String`, permitindo buscar parcialmente pelos valores informados.
- **Paginação**: A funcionalidade de paginação está disponível para retornar os resultados de maneira organizada e eficiente, utilizando o objeto `Pageable`.

## Exemplo de Uso

Abaixo um exemplo de uso do serviço para buscar clientes:

```java
@Autowired
private ClienteService clienteService;

public void exemploDeUso() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Cliente> clientes = clienteService.buscarClientes(null, "João", null, null, null, null, pageable);
    clientes.forEach(cliente -> System.out.println(cliente.getNome()));
}
```

## Executando o Projeto

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Navegue até a pasta do projeto:
   ```bash
   cd cliente-api
   ```

3. Execute o projeto com o Maven:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints Disponíveis

Atualmente, a principal funcionalidade disponível é a busca de clientes. Para acessar a API, é necessário enviar uma requisição para o endpoint de busca, utilizando os parâmetros desejados.

- **GET /clientes**: Busca clientes com os parâmetros opcionais, por exemplo:
  ```
  GET /clientes?id=1&nome=João
  ```

## Contribuições

Sinta-se à vontade para contribuir com o projeto enviando pull requests ou abrindo issues no repositório.

## Contato

Caso tenha dúvidas ou sugestões, entre em contato:
- **Email**: [wesley@exemplo.com](mailto:wesley@exemplo.com)

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo LICENSE para mais detalhes.
