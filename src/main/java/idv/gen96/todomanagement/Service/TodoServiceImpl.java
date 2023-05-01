package idv.gen96.todomanagement.Service;

import idv.gen96.todomanagement.DTO.TodoDTO;
import idv.gen96.todomanagement.Entity.Todo;
import idv.gen96.todomanagement.Exception.ResourceNotFoundException;
import idv.gen96.todomanagement.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        //將TodoDTO轉換成Todo entity，使用modelMapper就不需要寫重複性高的程式碼
        Todo todo = modelMapper.map(todoDTO, Todo.class);

        //儲存轉換後的Todo entity
        Todo savedTodo = todoRepository.save(todo);

        //將儲存後的Todo entity轉換成TodoDTO
        TodoDTO savedTodoDTO = modelMapper.map(savedTodo, TodoDTO.class);

        return savedTodoDTO;
    }

    @Override
    public TodoDTO getTodo(long id) {
        //找尋指定id的資料，當找不到時會就回傳Todo not found with id:[0-9]*，而不是ResourceNotFoundException
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        return modelMapper.map(todo, TodoDTO.class);
    }

    //取得全部的Todos
    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    //修改Todo的資料
    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO, long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todo.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    //刪除指定id的todo
    @Override
    public void deleteTodo(long id) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todoRepository.deleteById(id);
    }

    //將指定id的completd設定成true
    @Override
    public TodoDTO completeTodo(long id) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    //將指定id的completd設定成false
    @Override
    public TodoDTO incompleteTodo(long id) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }
}
