package by.boginsky.services;

import by.boginsky.domain.Todo;
import by.boginsky.domain.plaintobjects.TodoPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoService {

    TodoPojo createTodo(Todo todo, Long userId);

    TodoPojo getTodo(long id);

    List<TodoPojo> getAllTodos(Long userId);

    TodoPojo updateTodo(Todo todo, long todoId);

    String deleteTodo(long id);
}
