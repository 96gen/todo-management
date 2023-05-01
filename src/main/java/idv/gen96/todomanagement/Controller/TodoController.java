package idv.gen96.todomanagement.Controller;

import idv.gen96.todomanagement.DTO.TodoDTO;
import idv.gen96.todomanagement.Entity.Todo;
import idv.gen96.todomanagement.Service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //GET http://localhost:8080/api/todos
    //取得全部Todo資料
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    //PUT http://localhost:8080/api/todos/{id}
    //修改指定id的Todo資料
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO, @PathVariable("id") long id){
        TodoDTO updateTodoDTO = todoService.updateTodo(todoDTO, id);
        return ResponseEntity.ok(updateTodoDTO);
    }

    //DELETE http://localhost:8080/api/todos/{id}
    //刪除指定id的todo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully!");
    }

    //只設定一個值，不是多個值，所以用PATCH
    //PATCH http://localhost:8080/api/todos/{id}/complete
    //將指定id的completd設定成true
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") long id){
        TodoDTO updatedTodoDTO = todoService.completeTodo(id);
        return ResponseEntity.ok(updatedTodoDTO);
    }

    //PATCH http://localhost:8080/api/todos/{id}/in-complete
    //將指定id的completd設定成false
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoDTO> incompleteTodo(@PathVariable("id") long id){
        TodoDTO updatedTodoDTO = todoService.incompleteTodo(id);
        return ResponseEntity.ok(updatedTodoDTO);
    }
}
