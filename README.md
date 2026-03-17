# Client Address API

API REST para consulta de endereço a partir de CEP e validação de CNPJ, com fallback automático entre provedores externos.

---

## Tecnologias

- Java 17
- Spring Boot 4
- RestClient (Spring 6)
- Spring Retry
- WireMock (testes de integração)
- Mockito (testes unitários)
- Maven

---

## Arquitetura

O projeto segue os princípios da **Arquitetura Hexagonal**, organizado nas seguintes camadas:

```
adapter/
├── in/
│   └── http/          ← Controllers, DTOs de request/response, validadores
└── out/
    └── gateway/       ← Clientes HTTP externos (BrasilAPI, ViaCEP)

application/
└── usecase/           ← Casos de uso (regras de orquestração)

domain/
├── model/             ← Modelos de domínio
├── ports/             ← Interfaces (contratos de entrada e saída)
└── validation/        ← Validadores puros (CEP, CNPJ)
```

### Estratégia de fallback

A consulta de endereço utiliza dois provedores externos com fallback automático:

```
BrasilAPI  →  (falha)  →  ViaCEP
```

Quando a BrasilAPI retorna erro ou dados incompletos, a requisição é automaticamente redirecionada para o ViaCEP.

---

## Endpoints

### GET `/api/v1/client/address/:cep/:cnpj`

Retorna o endereço correspondente ao CEP informado, validando o CNPJ.

**Exemplo de requisição:**

```bash
curl --location 'http://localhost:8080/api/v1/client/address/62900000/11222333000181'
```

**Resposta de sucesso — 200 OK:**

```json
{
    "zip_code": "62900000",
    "street": "Rua Coronel Alexanzito",
    "neighborhood": "Centro",
    "city": "Russas",
    "state": "CE"
}
```

**Resposta de erro:**

```json
{
    "message": "CNPJ invalid",
    "code": 400
}
```

---

## Códigos de resposta

| Status | Descrição                                                |
|--------|----------------------------------------------------------|
| `200 OK` | Endereço encontrado com sucesso                          |
| `400 Bad Request` | CEP ou CNPJ inválido                                     |
| `404 Not Found` | Endereço do CEP não deu match com o encontrado pelo cnpj |
| `500 Internal Server Error` | Erro inesperado                                          |

---

## Como executar

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Rodando a aplicação

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

---

## Testes

### Testes unitários

Testam os casos de uso e validadores de forma isolada com **Mockito**, sem subir o contexto Spring.

```bash
mvn test
```

### Testes de integração

Testam o fluxo completo (controller → usecase → gateway) com **WireMock** simulando as APIs externas, sem depender de internet.

```bash
mvn verify
```

### Todos os testes

```bash
mvn verify
```

---

## Variáveis de ambiente

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `RETRY_MAX_ATTEMPTS` | Número de tentativas antes do fallback | `3` |
| `RETRY_WAIT_DURATION` | Tempo de espera entre tentativas | `500ms` |
