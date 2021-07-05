package by.boginsky.services;

import by.boginsky.domain.User;
import by.boginsky.domain.plaintobjects.UserPojo;

import java.util.List;

public interface IUserService {
    UserPojo createUser(User user);

    UserPojo findUserByEmailAndPassword(String email, String password);

    UserPojo getUser(long id);

    List<UserPojo> getAllUsers();

    UserPojo updateUser(User user, long id);

    String deleteUser(long id);

}
