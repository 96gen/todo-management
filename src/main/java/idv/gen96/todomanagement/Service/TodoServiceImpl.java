package idv.gen96.todomanagement.Service;

import idv.gen96.todomanagement.DTO.TodoDTO;
import idv.gen96.todomanagement.Entity.Todo;
import idv.gen96.todomanagement.Exception.ResourceNotFoundException;
import idv.gen96.todomanagement.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
