# Projeto Pedidos API

## Descrição
O projeto **Pedidos API** é uma aplicação desenvolvida para gerenciamento de pedidos. Este README contém as instruções para configuração e inicialização da aplicação, bem como exemplos de configuração do banco de dados.

---

## Iniciando o Projeto

### Executando o JAR
Execute o comando abaixo para iniciar a aplicação com as variáveis de configuração:

```bash
java -jar -Dserver.port=8080 \
  -DDB_URL="jdbc:mysql://localhost:3306/api_pedidos?useSSL=false&serverTimezone=UTC" \
  -DDB_USERNAME=acelera \
  -DDB_PASSWORD=programador \
  -DBASE_URL_MULTT=https://api.multt.digital/api/pagamentos \
  -DTOKEN_MULTT=59c6d7b0-1180-4aeb-ae0a-c7d30f464a73 \
  gerenciamento-pedidos-0.0.1-SNAPSHOT.jar
```

### Configurações do Ambiente
Para configurar as variáveis de ambiente, use o formato abaixo:

```bash
DB_PASSWORD=programador;
DB_URL=jdbc:mysql://localhost:3306/api_pedidos?useSSL=false&serverTimezone=UTC;
DB_USERNAME=acelera;
BASE_URL_MULTT=https://api.multt.digital/api/pagamentos;
TOKEN_MULTT=59c6d7b0-1180-4aeb-ae0a-c7d30f464a73;
SERVER_PORT=8080
```

---

## Configuração Inicial do Banco de Dados

### Inserindo Usuário Padrão
Use o comando abaixo para criar um usuário inicial no banco de dados:

```sql
INSERT INTO usuario (id, email, nome, senha) 
VALUES (1, 'wesley@gmail.com', 'Wesley Eduardo', '$2a$10$4wwRH6NMIEHLc7D7Inf11ub1sC4/cMTwdhHEsbcCq6kIwUcE1F7dK');
```

### Inserindo Perfis de Usuário
Adicione os perfis de usuário com os seguintes comandos:

```sql
INSERT INTO role (id, role_type) VALUES (1, 'ROLE_USUARIO');
INSERT INTO role (id, role_type) VALUES (2, 'ROLE_ADMINISTRADOR');
INSERT INTO role (id, role_type) VALUES (3, 'ROLE_GERENTE');
```

### Associando Perfis ao Usuário
Associe os perfis ao usuário criado anteriormente com os comandos:

```sql
INSERT INTO roles_usuario (usuario_id, role_id) VALUES (1, 1);
INSERT INTO roles_usuario (usuario_id, role_id) VALUES (1, 2);
INSERT INTO roles_usuario (usuario_id, role_id) VALUES (1, 3);
```

---

## Executar API via Docker
- Acessar via terminar a raiz do projeto e executar o comando abaixo
- docker compose up -d

## Swagger Integração pagamentos
https://api.multt.digital/swagger

## Contato
Para dúvidas ou sugestões, entre em contato:
- Email: wesley@aceleraprogramador.com.br