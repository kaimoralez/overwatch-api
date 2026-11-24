# Overwatch Tactical & Roster API

## Descrição do Projeto
Aplicação Full Stack composta por uma API REST em Java com Spring Boot e uma interface Web integrada para gerenciar dados competitivos de Overwatch.

* **Problema atendido:** Substitui o controle manual descentralizado feito em planilhas e anotações por um sistema web centralizado para cadastro, consulta, edição e exclusão de registros[cite: 1].
* **Público usuário:** Treinadores (Coaches), capitães de equipe e organizadores de torneios amadores de Overwatch.
* **Funcionalidades:** CRUD completo (listar, buscar por ID, cadastrar, atualizar e excluir) para 5 entidades: **Heróis, Jogadores, Equipes, Mapas e Partidas**.

## Tecnologias Utilizadas
* **Back-end:** Java 25, Spring Boot 3, Spring Web, Spring Data JPA, Bean Validation
* **Banco de Dados:** MySQL
* **Documentação:** Swagger / OpenAPI (`springdoc-openapi`)
* **Front-end:** HTML5, CSS3 e JavaScript puro (Fetch API) servidos via `/static`
* **Build:** Maven (empacotamento `.jar`)

## Pré-requisitos
* JDK 25 instalado
* Maven 3.8+
* MySQL Server rodando localmente

## Configuração do Banco e Variáveis de Ambiente
O projeto utiliza variáveis de ambiente para não expor senhas no repositório
* `DB_URL` (padrão: `jdbc:mysql://localhost:3306/overwatch_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC`)
* `DB_USER` (padrão: `root`)
* `DB_PASS` (padrão: `root`)

As tabelas são criadas automaticamente pelo Hibernate (`ddl-auto=update`). Para popular o banco com dados iniciais de exemplo, execute o script `database.sql` no seu cliente MySQL.

## Como Executar
1. Clone o repositório:
   ```bash
   git clone: https://github.com/kaimoralez/overwatch-api.git
