package idv.gen96.todomanagement.Service;

import idv.gen96.todomanagement.DTO.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO addTodo(TodoDTO todoDTO);

    TodoDTO getTodo(long id);

    List<TodoDTO> getAllTodos();
}
