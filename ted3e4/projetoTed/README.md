# Sistema de Cadastro de Contas

## Requisitos

- Java 26
- Maven
- MySQL
- JavaFX 26

## Banco de dados

1. Execute `CriacaoTabelas.sql` no MySQL.
2. Configure `src/main/resources/db.properties`:

```properties
bd.server=localhost
bd.port=3306
bd.database=banco_db
bd.user=SEU_USUARIO
bd.password=SUA_SENHA
```

## Execução

Na raiz do projeto:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```

No IntelliJ ou SceneBuilder, execute a classe `Main` e utilize os arquivos FXML em `src/main/resources/view`.

## Funcionalidades

- Cadastro de conta corrente ou poupança
- Listagem e consulta pelo número
- Alteração e exclusão
- Depósito e saque
- Extrato de operações
- Persistência no MySQL
