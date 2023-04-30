package idv.gen96.todomanagement.Controller;

import idv.gen96.todomanagement.DTO.TodoDTO;
import idv.gen96.todomanagement.Service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    //POST http://localhost:8080/api/todos
    //建立Todo
    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO){
        TodoDTO savedTodo = todoService.addTodo(todoDTO);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //GET http://localhost:8080/api/todos/{id}
    //傳入id查詢todo
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable("id") long todoId){
        TodoDTO todoDTO = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }
}
