package by.boginsky.services;

import by.boginsky.domain.Todo;
import by.boginsky.domain.plaintobjects.TodoPojo;

import java.util.List;

public interface ITodoService {
    TodoPojo createTodo(Todo todo, Long userId);

    TodoPojo getTodo(Long id, Long userId);

    TodoPojo updateTodo(Todo updatedTodo, Long todoId, Long userId);

    String deleteTodo(Long id, Long userId);

    List<TodoPojo> getAllTodos(Long userId);

}
