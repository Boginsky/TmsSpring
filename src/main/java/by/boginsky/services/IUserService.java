package by.boginsky.services;

import by.boginsky.domain.User;
import by.boginsky.domain.plaintobjects.UserPojo;

import java.util.List;

public interface IUserService {

    List<UserPojo> getAllUsers();

    UserPojo createUser(User user);

    UserPojo getUser(long id);

    UserPojo updateUser(User user, long id);

    String deleteUser(long id);
}
