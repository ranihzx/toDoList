# 📝 To-Do List API – Spring Boot

## 👩‍💻 Integrantes  
- **Raniele Conceição Pereira**  
- **Maria Eduarda Kernitski**  
- **Raul Alexandre Soares Lao**

---

## 🎯 Objetivo  

Este projeto foi desenvolvido como parte da disciplina de **Desenvolvimento Web com Spring Boot**, com o objetivo de construir uma **API RESTful** completa para o gerenciamento de tarefas (*To-Do List*).  
A aplicação permite **criar, listar, atualizar e excluir** tarefas, aplicando conceitos de **Spring Boot, Spring Data JPA, PostgreSQL** e **boas práticas de arquitetura MVC**.

---

## ⚙️ Tecnologias Utilizadas  

- **Java 17**  
- **Spring Boot 3.x**  
- **Maven**  
- **PostgreSQL**  
- **Spring Web**  
- **Spring Data JPA**  
- **Spring Boot DevTools**  
- **Jakarta Validation**  

---

## 🧩 Estrutura do Projeto  

O projeto foi organizado em três camadas principais:

```
dw.toDoList
 ├── model
 │   ├── control
 │       └── TarefaController.java
 │   ├── repository
 │       └── TarefaRepository.java
 │   └── Tarefa.java
 └── ToDoListApplication.java
```

### 📂 `model`  
Contém a entidade **Tarefa**, que representa a tabela no banco de dados.  

```java
@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100)
    @NotBlank
    private String descricao;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean concluida;

    @Column
    @NotNull
    @Min(1) @Max(5)
    private int prioridade;

    [...]
}
```

---

### 📂 `repository`  
Interface responsável pela comunicação com o banco de dados, utilizando **Spring Data JPA**.

```java
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByDescricaoContaining(String descricao);

    List<Tarefa> findByConcluida(boolean status);
}
```

---

### 📂 `control`  
Controlador REST que define os endpoints da aplicação.  
Todos os endpoints seguem o padrão RESTful, retornando respostas com status HTTP adequados.

Principais métodos:

| Método | URL | Descrição |
|---------|-----|------------|
| **POST** | `/tarefas-api/` | Cria uma nova tarefa |
| **GET** | `/tarefas-api/` | Lista todas as tarefas (com filtro opcional por descrição) |
| **GET** | `/tarefas-api/{id}` | Busca uma tarefa pelo ID |
| **PUT** | `/tarefas-api/{id}` | Atualiza uma tarefa existente |
| **DELETE** | `/tarefas-api/{id}` | Deleta uma tarefa pelo ID |
| **GET** | `/tarefas-api/pendentes` | Lista apenas as tarefas não concluídas |
| **PATCH** | `/tarefas-api/{id}/concluir` | Marca uma tarefa como concluída |

---

## 🛠️ Configuração do Projeto  

O arquivo `application.properties` contém as configurações principais:

```properties
server.servlet.context-path=/tarefas-api
server.port=8081

### Database configuration ###
spring.datasource.url=jdbc:postgresql://localhost:5432/tarefasdb
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Obs: ```spring.datasource.username=``` e ```spring.datasource.password=``` devem ser preenchidos de acordo com o BD local

---
