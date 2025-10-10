# InkSpace - Sistema de Gerenciamento de Biblioteca

InkSpace é um sistema de gerenciamento de biblioteca desenvolvido com Spring Boot. A aplicação permite o controle completo de livros, autores, membros e empréstimos, oferecendo tanto uma interface web para administração quanto uma API REST para integrações externas.

Este projeto foi construído como um exercício prático para aprender e aplicar os conceitos do ecossistema Spring, incluindo Spring MVC, Spring Data JPA, Thymeleaf e validação de dados.

## ✨ Funcionalidades

-   **Gerenciamento de Entidades:** CRUD completo para Livros, Autores, Categorias e Membros.
-   **Interface Web:** Páginas administrativas construídas com Thymeleaf para fácil visualização, cadastro, edição e exclusão de dados.
-   **API REST:** Endpoints para todas as entidades, permitindo a integração com outras aplicações (testado com Postman).
-   **Criação em Lote:** Endpoints de API para cadastrar múltiplos autores e categorias de uma só vez, facilitando o povoamento do banco de dados.
-   **Validação:** Regras de validação no back-end para garantir a integridade dos dados (ex: não permitir cópias negativas).
-   **UI Interativa:** Uso de JavaScript e da biblioteca Select2 para melhorar a experiência do usuário nos formulários.

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
    -   MySQL
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
-   Postman (para testar a API REST)

### 2. Configuração do Banco de Dados
1.  Inicie seu servidor MySQL.
2.  Crie um novo banco de dados. Exemplo: `CREATE DATABASE biblioteca_db;`
3.  Abra o arquivo `src/main/resources/application.properties` e configure suas credenciais de acesso ao banco:

    ```properties
    # URL de conexão com o banco de dados
    spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db?createDatabaseIfNotExist=true

    # Usuário e senha do seu banco MySQL
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha

    # Configurações do Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

### 3. Clonar e Executar
1.  Clone este repositório:
    ```bash
    git clone [https://github.com/Marcosssrf/InkSpace]
    ```
2.  Navegue até a pasta do projeto e execute com o Maven:
    ```bash
    mvn spring-boot:run
    ```
3.  A aplicação estará disponível em `http://localhost:8080`.

## 📚 Populando o Banco de Dados (via API)

A aplicação iniciará com o banco de dados vazio. Use o Postman para cadastrar os dados iniciais de forma rápida. **A ordem é importante.**

1.  **Cadastre as Categorias:** Envie uma requisição `POST` para `http://localhost:8080/api/categorias/batch` com o JSON das categorias.
2.  **Cadastre os Autores:** Envie uma requisição `POST` para `http://localhost:8080/api/autores/batch` com o JSON dos autores.
3.  **Cadastre os Livros:** Envie uma requisição `POST` para `http://localhost:8080/api/livros/batch` com o JSON dos livros.

## 📖 Documentação da API (Endpoints)

Use o Postman ou outra ferramenta de sua preferência para interagir com a API.

**Header Padrão para requisições `POST` e `PUT`:** `Content-Type: application/json`

---
### Autores (`/api/autores`)
-   **`POST /api/autores`**: Cria um novo autor.
    -   **Body:** `{ "nome": "Jorge", "sobrenome": "Amado" }`
-   **`POST /api/autores/batch`**: Cria vários autores.
    -   **Body:** `[ { "nome": "Autor 1", ... }, { "nome": "Autor 2", ... } ]`
-   **`GET /api/autores`**: Lista todos os autores.

---
### Categorias (`/api/categorias`)
-   **`POST /api/categorias`**: Cria uma nova categoria.
    -   **Body:** `{ "nomeCategoria": "Suspense" }`
-   **`POST /api/categorias/batch`**: Cria várias categorias.
    -   **Body:** `[ { "nomeCategoria": "Cat 1" }, { "nomeCategoria": "Cat 2" } ]`
-   **`GET /api/categorias`**: Lista todas as categorias.

---
### Livros (`/api/livros`)
-   **`POST /api/livros`**: Cria um novo livro com suas associações.
    -   **Body:** (Veja o exemplo complexo de `LivroRequestDTO` que montamos)
-   **`POST /api/livros/batch`**: Cria vários livros.
-   **`GET /api/livros`**: Lista todos os livros.
-   **`GET /api/livros/{id}`**: Busca um livro por ID.
-   **`PUT /api/livros/{id}`**: Atualiza um livro.
-   **`DELETE /api/livros/{id}`**: Deleta um livro.

## 🌐 Acessando a Interface Web

-   **Página Inicial:** `http://localhost:8080/`
-   **Livros:** `http://localhost:8080/livros/lista`
-   **Membros:** `http://localhost:8080/membros/lista`
-   **Autores:** `http://localhost:8080/autores/lista`
-   **Categorias:** `http://localhost:8080/categorias/lista`
