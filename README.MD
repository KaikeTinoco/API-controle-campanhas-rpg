# API de Controle de Campanhas de RPG 🎲

![ai-gen](https://img.shields.io/badge/ai--gen-ready-blue) ![Java](https://img.shields.io/badge/Java-17-informational) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen)

**API REST para gerenciar campanhas e personagens de RPG**, criada para integrar com os bots:
- https://github.com/KaikeTinoco/Bot-Gerador-de-Hist-ria
- https://github.com/KaikeTinoco/Bot-Gerador-de-Personagens

Esta API permite criar/consultar/atualizar campanhas e personagens, persistindo via JPA e expondo documentação automática via OpenAPI/Swagger.

Índice
- Visão Geral
- Tecnologias
- Requisitos
- Quick Start (executar localmente)
- Endpoints principais (tabela)
- Exemplos Rápidos (cURL / JSON)
- Documentação (Swagger / OpenAPI)
- Configuração de banco de dados
- Testes
- Estrutura do projeto
- Tratamento de Erros
- Contribuição
- Contato

---

## 🔎 Visão Geral
**O que faz:** gerencia campanhas (contexto, notas, personagens) e personagens (NPCs/jogadores).  
**Principais responsabilidades:** CRUD de Campanha e Personagem, validações simples, persistência JPA e documentação OpenAPI.

---

## 🧰 Tecnologias
- **Java 17**
- **Spring Boot 3.4.5** (Web, Data JPA, Validation)
- **springdoc-openapi** (Swagger UI)
- **Lombok**
- **H2** (dev) e **PostgreSQL** (opcional produção)
- **Maven** (wrapper incluído)

---

## ✅ Requisitos
- Java 17+
- Maven (opcional — use o wrapper `./mvnw`)
- (Opcional) PostgreSQL para produção

---

## 🚀 Quick Start (executar localmente)

1) Rodar com Maven Wrapper

Linux / macOS:
```bash
./mvnw spring-boot:run
```

Windows (cmd/powershell):
```powershell
mvnw.cmd spring-boot:run
```

2) Gerar jar e executar:
```bash
./mvnw clean package
java -jar target/api-0.0.1-SNAPSHOT.jar
```

A aplicação inicia por padrão em:
http://localhost:8080

Executar testes:
```bash
./mvnw test
```

---

## 🧭 Endpoints principais

Base URL (local): `http://localhost:8080`

| Request | Descrição | Parâmetros | Resultado |
|---|---:|---|---|
| POST /campanha/criar | **Cria uma nova campanha** a partir de CampanhaDTO | Body (JSON): CampanhaDTO { nome, sistema, descricaoHistoria, restricoes: [String] } | 201 CREATED — retorna objeto Campanha salvo (id, timestamps, listas inicializadas). Validações: campos obrigatórios; 400 se ausentes. |
| GET /campanha/buscaGeral | **Lista todas as campanhas** | nenhum | 200 OK — array de Campanha |
| GET /campanha/buscaPorNome?nome={nome} | **Busca campanha por nome** (exato) | Query: nome (String, obrigatório) | 200 OK — Campanha | 404 se não encontrada; 400 se nome inválido |
| GET /campanha/buscaPorSistema?sistema={sistema} | **Lista campanhas por sistema** | Query: sistema (String, obrigatório) | 200 OK — lista de Campanha | 404 se nenhuma; 400 se parâmetro inválido |
| DELETE /campanha/deletar?id={id} | **Deleta campanha por id** | Query: id (Long, obrigatório) | 200 OK — mensagem de sucesso | 400 se id nulo; 404 se não existir |
| POST /campanha/atualizar | **Salva resumo/ação da última sessão** (adiciona ao contexto narrativo) | Body (JSON): ResumoDTO { nomeCampanha, resumoSessao } | 200 OK — mensagem "Campanha atualizada com sucesso" | 400 se campos nulos; 404 se campanha não encontrada |

| Request | Descrição | Parâmetros | Resultado |
|---|---:|---|---|
| POST /personagens/criar?campanhaId={campanhaId} | **Cria um personagem** (NPC ou jogador) e associa à campanha | Query: campanhaId (Long, obrigatório) Body (JSON): PersonagemDTO (veja exemplos) | 201 CREATED — objeto Personagem salvo; campanha atualizada com o personagem |
| GET /personagens/buscarTodos?campanhaId={id}&nomeCampanha={nome} | **Retorna todos os personagens de uma campanha** — informe ao menos `campanhaId` ou `nomeCampanha` | Query: campanhaId (opcional), nomeCampanha (opcional) — pelo menos um necessário | 200 OK — lista de Personagem | 400 se ambos ausentes |
| GET /personagens/busca?nomeCampanha={nomeCampanha}&nomePersonagem={nomePersonagem} | **Busca personagem específico** pelo nome dentro de uma campanha | Query: nomeCampanha (String), nomePersonagem (String) | 200 OK — Personagem | 404 se não encontrado |
| PUT /personagens/atualizar?nomeCampanha={nomeCampanha} | **Atualiza um personagem** — envie o objeto Personagem completo no body | Query: nomeCampanha (String, obrigatório) Body: Personagem (JSON) | 200 OK — objeto Personagem salvo/atualizado |

---

## ✏️ Exemplos Rápidos

Exemplo CampanhaDTO (body para POST /campanha/criar):
```json
{
  "nome": "A Jornada de X",
  "sistema": "Dungeons & Dragons 5e",
  "descricaoHistoria": "Uma história sobre dragões e um artefato perdido...",
  "restricoes": ["sem pvp", "sem magia divina elevada"]
}
```

cURL criar campanha:
```bash
curl -X POST "http://localhost:8080/campanha/criar" \
  -H "Content-Type: application/json" \
  -d '{ "nome":"A Jornada de X", "sistema":"Dungeons & Dragons 5e", "descricaoHistoria":"Uma história sobre dragões...", "restricoes":["sem pvp"] }'
```

Exemplo mínimo PersonagemDTO (body para POST /personagens/criar?campanhaId=1):
```json
{
  "nome":"Gorim",
  "idade":40,
  "sexo":"MASCULINO",
  "nivel":5,
  "classe":"Guerreiro",
  "forca":16,
  "destreza":12,
  "constituicao":14,
  "npc":false
}
```

cURL criar personagem:
```bash
curl -X POST "http://localhost:8080/personagens/criar?campanhaId=1" \
  -H "Content-Type: application/json" \
  -d '{ "nome":"Gorim", "idade":40, "nivel":5, "classe":"Guerreiro", "forca":16, "destreza":12, "constituicao":14, "npc":false }'
```

---

## 🧾 Documentação (Swagger / OpenAPI)
A aplicação expõe OpenAPI via springdoc. Após iniciar a aplicação acesse:
- **Swagger UI:** http://localhost:8080/swagger-ui.html  
  ou http://localhost:8080/swagger-ui/index.html
- **JSON OpenAPI:** http://localhost:8080/v3/api-docs

---

## 🗄️ Banco de Dados

H2 (desenvolvimento — in-memory, já incluido):
Exemplo application.properties (H2):
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

PostgreSQL (produção — configurar credenciais):
```
spring.datasource.url=jdbc:postgresql://host:5432/seu_banco
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

## 🧪 Testes
Rodar suíte de testes:
```bash
./mvnw test
```

---

## 🧩 Estrutura do Projeto (resumo)
- com.API.Controle.Rpg.api
    - ApiApplication.java — ponto de entrada Spring Boot
    - config/SwaggerConfiguration.java — OpenAPI info
    - resource/ — controllers REST (CampanhaController, PersonagemController)
    - services/ — lógica de negócio (CampanhaService, PersonagemService)
    - repositories/ — Spring Data JPA repositories
    - domain/model/ — entidades JPA (Campanha, Personagem)
    - domain/dtos/ — DTOs (CampanhaDTO, PersonagemDTO, ResumoDTO)
    - exceptions/ — exceções customizadas e handler
    - mappers/ — (MapStruct mapper placeholder)

---

## ⚠️ Tratamento de Erros
- **400 Bad Request:** parâmetros ausentes ou inválidos (ex.: criação sem campos obrigatórios; listar personagens sem parâmetros).
- **404 Not Found:** campanha ou personagem não encontrados.
- Exceções customizadas definidas no código: `BadRequestException`, `NotFoundException`. Existe um handler central que normaliza respostas de erro (ErrorResponseDTO).

---

## 🤝 Contribuição
- Abra uma issue descrevendo a proposta ou problema.
- Faça fork do repositório, crie uma branch `feature/*` ou `bugfix/*` e envie um PR.
- Mantenha compatibilidade com Java 17 e siga o estilo do projeto.
- Para mudanças públicas, adicione uma licença adequada (veja abaixo).

---

## ✉️ Contato
- Autor: **KaikeTinoco** (repositório original)
- Para dúvidas ou sugestões: abra uma issue no repositório.

---

Obrigado por usar/avaliar o projeto! Se desejar, posso **gerar payloads mais completos**, criar uma **Postman collection** com as rotas, ou adicionar **exemplos de resposta** para cada endpoint. 🙌