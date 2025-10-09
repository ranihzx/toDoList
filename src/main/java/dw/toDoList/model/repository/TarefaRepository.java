package dw.toDoList.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.toDoList.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByDescricaoContaining(String descricao);

    List<Tarefa> findByConcluida(boolean status);
}