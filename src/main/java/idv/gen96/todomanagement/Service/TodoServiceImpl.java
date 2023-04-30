package idv.gen96.todomanagement.Service;

import idv.gen96.todomanagement.DTO.TodoDTO;
import idv.gen96.todomanagement.Entity.Todo;
import idv.gen96.todomanagement.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{
    private TodoRepository todoRepository;
    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        //將TodoDTO轉換成Todo entity
        Todo todo = new Todo();
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        //儲存轉換後的Todo entity
        Todo savedTodo = todoRepository.save(todo);

        //將儲存後的Todo entity轉換成TodoDTO
        TodoDTO savedTodoDTO = new TodoDTO();
        savedTodoDTO.setId(savedTodo.getId());
        savedTodoDTO.setTitle(savedTodo.getTitle());
        savedTodoDTO.setDescription(savedTodo.getDescription());
        savedTodoDTO.setCompleted(savedTodo.isCompleted());

        return savedTodoDTO;
    }
}
