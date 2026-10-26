# APIs de Cliente e Pedido

Este projeto usa Spring Boot, Spring Data JPA e MySQL. As entidades são mapeadas pelo JPA para tabelas; os repositórios `JpaRepository` fazem as operações no banco; e os controllers expõem as rotas HTTP.

## Modelo usado

Como o ZIP recebido não trazia o enunciado nem as classes Cliente/Pedido, este exemplo assume:

- Cliente: `codigo` (gerado pelo banco), `nome`, `email` e `telefone`.
- Pedido: `codigo` (gerado pelo banco), `dataPedido`, `valor` e `cliente`.
- Cada Pedido pertence a um Cliente existente (`cliente_codigo`).

Se o professor definiu outros campos ou nomes, ajuste as entidades e os exemplos abaixo para coincidir com o enunciado.

## Banco de dados

O `application.properties` já aponta para o MySQL local, para o banco `lojinhaTarde`, e usa `createDatabaseIfNotExist=true`. Após instalar e iniciar o MySQL Server, confira se o usuário e a senha configurados nesse arquivo correspondem à sua instalação. Com `spring.jpa.hibernate.ddl-auto=update`, o Hibernate cria/atualiza as tabelas a partir das entidades ao iniciar a aplicação.

## Rotas

| Método | Caminho | Ação |
| --- | --- | --- |
| POST | `/api/cliente` | Cadastra cliente |
| PUT | `/api/cliente` | Altera cliente (informe `codigo`) |
| GET | `/api/cliente` | Lista clientes |
| GET | `/api/cliente/{codigo}` | Busca cliente |
| DELETE | `/api/cliente/{codigo}` | Remove cliente |
| POST | `/api/pedido` | Cadastra pedido para cliente existente |
| PUT | `/api/pedido` | Altera pedido (informe `codigo`) |
| GET | `/api/pedido` | Lista pedidos |
| GET | `/api/pedido/{codigo}` | Busca pedido |
| DELETE | `/api/pedido/{codigo}` | Remove pedido |

## Exemplos de JSON

Cadastro de cliente (`POST /api/cliente`):

```json
{
  "nome": "Ana Silva",
  "email": "ana@example.com",
  "telefone": "11999999999"
}
```

Cadastro de pedido (`POST /api/pedido`), supondo que exista um Cliente com código 1:

```json
{
  "dataPedido": "2026-10-26",
  "valor": 49.90,
  "cliente": {
    "codigo": 1
  }
}
```

O código do Cliente é validado antes de salvar o Pedido. Para alterar registros, envie também o `codigo` do registro no corpo JSON, como o controller de Produto faz.
