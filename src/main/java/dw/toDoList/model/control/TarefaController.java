package dw.toDoList.model.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import dw.toDoList.model.Tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import dw.toDoList.model.repository.TarefaRepository;

@RestController
public class TarefaController {
    @Autowired
    TarefaRepository rep;

    @PostMapping("/")
    public ResponseEntity<Tarefa> createTarefa(@Valid @RequestBody Tarefa tarefa) {
        try {
            Tarefa novo = new Tarefa(tarefa.getDescricao(), tarefa.getPrioridade());
            Tarefa a = rep.save(novo);

            return new ResponseEntity<>(a, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Tarefa>> getAllTarefas(@RequestParam(required = false) String descricao) {
        try {
            List<Tarefa> tarefas = new ArrayList<Tarefa>();

            if(descricao == null) {
                rep.findAll().forEach(tarefas::add);
            } else {
                rep.findByDescricaoContaining(descricao).forEach(tarefas::add);
            }
            if(tarefas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable("id") long id) {
        Optional<Tarefa> tarefa = rep.findById(id);

        if(tarefa.isPresent()) {
            return new ResponseEntity<>(tarefa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable("id") long id, @Valid @RequestBody Tarefa tarefa) {
        Optional<Tarefa> data = rep.findById(id);

        if(data.isPresent()){
            Tarefa t = data.get();

            t.setDescricao(tarefa.getDescricao());
            t.setPrioridade(tarefa.getPrioridade());
            t.setConcluida(tarefa.isConcluida());

            return new ResponseEntity<>(rep.save(t), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTarefaById(@PathVariable("id") long id) {
        try {
            rep.deleteById(id); 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> getTarefasPendentes() {
        try {
            List<Tarefa> tarefas = new ArrayList<Tarefa>();
            rep.findByConcluida(false).forEach(tarefas::add);

            if(tarefas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(@PathVariable("id") long id) {
            Optional<Tarefa> tarefa = rep.findById(id);

        if(tarefa.isPresent()) {
            Tarefa tarefaAtualizada = tarefa.get();
            tarefaAtualizada.setConcluida(true);
            
            return new ResponseEntity<>(rep.save(tarefaAtualizada), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}