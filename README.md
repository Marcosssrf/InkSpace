# InkSpace - Sistema de Gerenciamento de Biblioteca

  Um sistema completo de gerenciamento de biblioteca construído com o ecossistema Spring, oferecendo uma interface web administrativa e uma API RESTful robusta.

---

## ✨ Funcionalidades

O InkSpace é uma aplicação full-stack que permite o controle total sobre as operações de uma biblioteca, incluindo:

-   **📚 Gerenciamento de Acervo:** CRUD completo para **Livros**, **Autores** e **Categorias**.
-   **🧑‍🤝‍🧑 Gestão de Membros:** Cadastro e administração de membros, com controle de status (Ativo, Inativo, etc.).
-   **🔄 Ciclo de Empréstimos:** Lógica de negócio para realizar empréstimos, com decremento automático do estoque de livros.
-   **💰 Sistema de Multas:** Geração automática de multas por atraso na devolução, com um painel para visualização e registro de pagamentos.
-   **🔖 Fila de Reservas:** Permite que membros reservem livros sem estoque. O sistema gerencia a fila e "guarda" o livro para o próximo da fila quando uma cópia é devolvida.
-   **📊 Painel de Relatórios:** Exibe estatísticas úteis, como o "Top 10 Livros Mais Emprestados".
-   **🚀 API RESTful:** Endpoints completos para todas as entidades, permitindo integrações e gerenciamento de dados via ferramentas como o Postman. Inclui endpoints para criação em lote (`/batch`).
-   **🎨 Interface Web Moderna:** Um painel administrativo (Dashboard) e páginas de gerenciamento construídas com Thymeleaf e um design consistente (tema escuro).
-   **💡 UI Interativa:** Uso de JavaScript e da biblioteca Select2 para criar campos de busca inteligentes nos formulários, melhorando a experiência do usuário.

## 🛠️ Tecnologias Utilizadas

-   **Backend:**
    -   Java 21
    -   Spring Boot 3.x
    -   Spring Web (MVC & REST)
    -   Spring Data JPA (com Hibernate)
    -   Bean Validation
-   **Frontend:**
    -   Thymeleaf
    -   HTML5 & CSS3
    -   JavaScript (com jQuery e Select2)
-   **Banco de Dados:**
    -   MySQL 8+ (para desenvolvimento local)
    -   PostgreSQL (configurado para produção)
-   **Build & Dependências:**
    -   Apache Maven
    -   Lombok
-   **Deploy:**
    -   Docker

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local.

### 1. Pré-requisitos
-   JDK 21 ou superior
-   Apache Maven 3.6+
-   MySQL Server 8.0+
-   Docker (opcional, para rodar em contêiner)
-   Uma IDE de sua preferência (IntelliJ, VSCode, etc.)
-   Postman (para popular o banco e testar a API REST)

### 2. Configuração do Banco de Dados
1.  Inicie seu servidor MySQL.
2.  Crie um novo banco de dados. Exemplo: `CREATE DATABASE biblioteca2;`
3.  O projeto está configurado para usar perfis. Para rodar localmente, você precisa ativar o perfil `dev`.
    -   **Se usar IntelliJ:** Vá em `Run` -> `Edit Configurations...`, selecione sua aplicação e no campo **"Active profiles"**, digite `dev`.
    -   O arquivo `src/main/resources/application.yml` já está pré-configurado com as credenciais padrão para o banco `biblioteca2` no localhost. Ajuste o usuário e senha se necessário.

### 3. Executando a Aplicação
1.  Clone este repositório:
    ```bash
    git clone [https://github.com/Marcosssrf/InkSpace.git](https://github.com/Marcosssrf/InkSpace.git)
    ```
2.  Navegue até a pasta do projeto.
3.  Você pode executar diretamente pela sua IDE ou usando o Maven Wrapper:
    ```bash
    ./mvnw spring-boot:run -Dspring.profiles.active=dev
    ```
4.  A aplicação estará disponível em `http://localhost:8080`.

## 📚 Populando o Banco de Dados (Obrigatório)

A aplicação iniciará com o banco de dados vazio. Use o Postman para cadastrar os dados iniciais de forma rápida. **A ordem é muito importante.**

1.  **Cadastre os Status:** Insira os dados nas tabelas `status_membro` e `status_reserva` usando os scripts SQL fornecidos no projeto (ou manualmente).
2.  **Cadastre as Categorias:** Envie uma requisição `POST` para `http://localhost:8080/api/categorias/batch` com o JSON de categorias.
3.  **Cadastre os Autores:** Envie uma requisição `POST` para `http://localhost:8080/api/autores/batch` com o JSON de autores.
4.  **Cadastre os Membros:** Use a interface web em `http://localhost:8080/membros/novo` para criar alguns membros.
5.  **Cadastre os Livros:** Envie uma requisição `POST` para `http://localhost:8080/api/livros/batch` com o JSON de livros.

## 📖 Documentação da API (Endpoints Principais)

**Header Padrão para `POST` e `PUT`:** `Content-Type: application/json`

| Entidade | Método | URL | Descrição |
| :--- | :--- | :--- | :--- |
| **Autores** | `POST` | `/api/autores/batch` | Cria múltiplos autores |
| | `GET` | `/api/autores` | Lista todos os autores |
| **Categorias** | `POST` | `/api/categorias/batch`| Cria múltiplas categorias |
| | `GET` | `/api/categorias` | Lista todas as categorias |
| **Livros** | `POST` | `/api/livros/batch` | Cria múltiplos livros |
| | `GET` | `/api/livros` | Lista todos os livros |
| | `GET` | `/api/livros/{id}` | Busca um livro por ID |
| | `DELETE`| `/api/livros/{id}` | Deleta um livro |
| **Empréstimos** | `POST` | `/api/emprestimos` | Realiza um novo empréstimo |
| | `PUT` | `/api/emprestimos/{id}/devolver` | Registra a devolução de um livro |
| | `GET` | `/api/emprestimos` | Lista todos os empréstimos |
