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
 │   ├── Tarefa.java
 │   └── repository
 │       └── TarefaRepository.java
 ├── control
 │   └── TarefaController.java
 └── ToDoListApplication.java
```

### 📂 `model`  
Contém a entidade **Tarefa**, que representa a tabela no banco de dados.  

```java
@Entity
@Table(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private boolean concluida = false;

    @Column(nullable = false)
    private int prioridade;
}
```

---

### 📂 `repository`  
Interface responsável pela comunicação com o banco de dados, utilizando **Spring Data JPA**.

```java
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByDescricaoContaining(String descricao);
    List<Tarefa> findByConcluida(boolean concluida);
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
| **GET** | `/tarefas-api/pendentes` | Lista apenas as tarefas não concluídas |

---

## 🛠️ Configuração do Projeto  

O arquivo `application.properties` contém as configurações principais:

```properties
server.port=8081
server.servlet.context-path=/tarefas-api

spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=postgres
spring.datasource.password=suasenha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚀 Como Executar  

1. **Clonar o repositório:**  
   ```bash
   git clone https://github.com/usuario/toDoList.git
   ```

2. **Configurar o banco de dados PostgreSQL:**  
   - Criar um banco chamado `todolist`.
   - Ajustar usuário e senha no `application.properties`.

3. **Executar a aplicação:**  
   - No terminal, dentro da pasta do projeto:  
     ```bash
     mvn spring-boot:run
     ```
   - Ou, pelo IDE (NetBeans, IntelliJ, Eclipse), executar a classe principal `ToDoListApplication.java`.

4. **Testar os endpoints:**  
   - Usar ferramentas como **Talend API Tester** ou **Postman**.  
   - A API rodará em:  
     ```
     http://localhost:8081/tarefas-api/
     ```

---

## 🧠 Aprendizados  

Durante o desenvolvimento, o grupo pôde praticar:
- A criação de uma API REST completa com Spring Boot.  
- O uso de **JPA** para persistência de dados.  
- A estruturação de projetos em **camadas (MVC)**.  
- O uso de **requisições HTTP (GET, POST, PUT)** e **validação de dados**.  

---

## 💬 Considerações Finais  

Este projeto proporcionou uma experiência prática com o desenvolvimento backend em Java, aplicando conceitos essenciais de APIs RESTful e integração com banco de dados relacional.  
A aplicação está pronta para servir como base de um sistema de produtividade mais completo, que pode futuramente receber uma interface web ou mobile.