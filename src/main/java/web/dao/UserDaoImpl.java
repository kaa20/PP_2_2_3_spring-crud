package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

// Класс имитирует получение данных из БД
@Component
public class UserDaoImpl implements UserDao {

    private List<User> users = new ArrayList<>();

    public UserDaoImpl() {
        users.add(new User(1l,"Vi",25));
        users.add(new User(2l,"Jinx",23));
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

}
