package by.boginsky.utils;

import by.boginsky.domain.Tag;
import by.boginsky.domain.Todo;
import by.boginsky.domain.User;
import by.boginsky.domain.plaintobjects.TagPojo;
import by.boginsky.domain.plaintobjects.TodoPojo;
import by.boginsky.domain.plaintobjects.UserPojo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {

    public UserPojo userToPojo(User source){
        UserPojo result = new UserPojo();
        result.setEmail(source.getEmail());
        result.setPassword(source.getPassword());
        result.setId(source.getId());

        result.setTodoList(source
                .getTodoList()
                .stream()
                .map(todo -> todoToPojo(todo))
                .collect(Collectors.toSet()));

        return result;
    }

    public TodoPojo todoToPojo(Todo source) {

        TodoPojo result = new TodoPojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setComment(source.getComment());
        result.setStartDate(source.getStartDate());
        result.setEndDate(source.getEndDate());
        result.setImportant(source.getImportant());
        result.setPriority(source.getPriority());
        result.setUserId(source.getUser().getId());

        result.setTagList(source.
                getTagList()
                .stream()
                .map(tag -> tagToPojo(tag))
                .collect(Collectors.toSet()));

        return result;
    }

    public TagPojo tagToPojo(Tag source){
        TagPojo result = new TagPojo();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setTodoListIds(source
                .getTodoList()
                .stream()
                .map(todo -> todo.getId())
                .collect(Collectors.toSet()));

         return result;
    }
}
