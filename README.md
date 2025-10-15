# InkSpace - Sistema de Gerenciamento de Biblioteca

<div align="center">
  <img src="src/main/resources/static/img/logo.png" alt="Logo InkSpace" width="150px"/>
  <h1 align="center">InkSpace</h1>
</div>

<p align="center">
  Um sistema completo de gerenciamento de biblioteca construído com o ecossistema Spring, oferecendo uma interface web administrativa com tema escuro e uma API RESTful robusta.
</p>

---

## ✨ Funcionalidades

O InkSpace é uma aplicação full-stack que permite o controle total sobre as operações de uma biblioteca, incluindo:

-   **📚 Gerenciamento de Acervo:** CRUD completo para **Livros**, **Autores** e **Categorias**.
-   **🧑‍🤝‍🧑 Gestão de Membros:** Cadastro e administração de membros, com controle de status (Ativo, Inativo, etc.) a partir de uma tabela relacional.
-   **🔄 Ciclo de Empréstimos:** Lógica de negócio para realizar empréstimos, com decremento automático do estoque de livros e cálculo de data prevista para devolução.
-   **💰 Sistema de Multas:** Geração automática de multas por atraso na devolução, com um painel para visualização e registro de pagamentos.
-   **🔖 Fila de Reservas:** Permite que membros reservem livros sem estoque. O sistema gerencia a fila e "guarda" o livro para o próximo da fila quando uma cópia é devolvida, permitindo a retirada posterior.
-   **📊 Painel de Relatórios:** Exibe estatísticas úteis, como o "Top 10 Livros Mais Emprestados".
-   **🚀 API RESTful:** Endpoints completos para todas as entidades, permitindo integrações e gerenciamento de dados via ferramentas como o Postman. Inclui endpoints para criação em lote (`/batch`).
-   **🎨 Interface Web Moderna:** Um painel administrativo (Dashboard) e páginas de gerenciamento construídas com Thymeleaf, utilizando um design consistente e um elegante tema escuro.
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
-   **Build & Dependências:**
    -   Apache Maven
    -   Lombok

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local.

### 1. Pré-requisitos
-   JDK 21 ou superior
-   Apache Maven 3.6+
-   MySQL Server 8.0+
-   Uma IDE de sua preferência (IntelliJ, VSCode, etc.)
-   Postman (para popular o banco e testar a API REST)

### 2. Configuração do Banco de Dados
1.  Inicie seu servidor MySQL.
2.  Crie um novo banco de dados. Exemplo: `CREATE DATABASE biblioteca2;`
3.  O projeto utiliza o arquivo `application.properties` com as credenciais para o banco `biblioteca2` no localhost. Verifique se seu usuário e senha correspondem:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3006/biblioteca2
    spring.datasource.username=root
    spring.datasource.password=123456
    ```

### 3. Executando a Aplicação
1.  Clone este repositório:
    ```bash
    git clone [https://github.com/Marcosssrf/InkSpace.git](https://github.com/Marcosssrf/InkSpace.git)
    ```
2.  Navegue até a pasta do projeto.
3.  Você pode executar diretamente pela sua IDE (certifique-se de que não há outras aplicações rodando na porta 8080) ou usando o Maven Wrapper no terminal:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  A aplicação estará disponível em `http://localhost:8080`.

## 📚 Populando o Banco de Dados (Obrigatório)

A aplicação iniciará com as tabelas vazias. Use o Postman para cadastrar os dados iniciais de forma rápida. **A ordem é muito importante.**

1.  **Cadastre os Status:** Insira os dados nas tabelas `status_membro` e `status_reserva` usando scripts SQL ou manualmente.
2.  **Cadastre as Categorias:** Envie uma requisição `POST` para `http://localhost:8080/api/categorias/batch` com o JSON de categorias.
3.  **Cadastre os Autores:** Envie uma requisição `POST` para `http://localhost:8080/api/autores/batch` com o JSON de autores.
4.  **Cadastre os Membros:** Use a interface web em `http://localhost:8080/membros/novo` para criar alguns membros.
5.  **Cadastre os Livros:** Envie uma requisição `POST` para `http://localhost:8080/api/livros/batch` com o JSON de livros.
6.  **Simule Empréstimos:** Use a interface web para simular empréstimos e devoluções para gerar dados para as multas e relatórios.

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
| | `DELETE`| `/api/livros/{id}` | Deleta um livro |
| **Empréstimos** | `POST` | `/api/emprestimos` | Realiza um novo empréstimo |
| | `PUT` | `/api/emprestimos/{id}/devolver` | Registra a devolução de um livro |
| | `GET` | `/api/emprestimos` | Lista todos os empréstimos |

## 🌐 Acessando a Interface Web

-   **Página Inicial:** `http://localhost:8080/`
-   **Livros:** `http://localhost:8080/livros/lista`
-   **Membros:** `http://localhost:8080/membros/lista`
-   **Autores:** `http://localhost:8080/autores/lista`
-   **Categorias:** `http://localhost:8080/categorias/lista`
-   **Empréstimos:** `http://localhost:8080/emprestimos/lista`
-   **Reservas:** `http://localhost:8080/reservas/lista`
-   **Multas:** `http://localhost:8080/multas/lista`
-   **Relatórios:** `http://localhost:8080/relatorios`
