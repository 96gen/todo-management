package idv.gen96.todomanagement.Service;

import idv.gen96.todomanagement.DTO.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO createTodo(TodoDTO todoDTO);

    TodoDTO getTodo(long id);

    List<TodoDTO> getAllTodos();

    TodoDTO updateTodo(TodoDTO todoDTO, long id);

    void deleteTodo(long id);

    TodoDTO completeTodo(long id);

    TodoDTO incompleteTodo(long id);
}
