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
    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO){
        TodoDTO savedTodo = todoService.addTodo(todoDTO);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
}
