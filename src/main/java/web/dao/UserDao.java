package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    User findById(long id);
    void update(long id, User user);
    void delete(long id);
    List<User> listUsers();

}
