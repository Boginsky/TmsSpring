package by.boginsky.repositories;

import by.boginsky.domain.Todo;
import by.boginsky.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
