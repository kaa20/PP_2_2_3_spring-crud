package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

// Класс имитирует получение данных из БД
@Repository
public class UserDaoImpl implements UserDao {

//    @Autowired
//    private SessionFactory sessionFactory;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User for ID " + id);
        }
        return user;
    }

    @Override
    public void update(long id, User user) {
        User userToBeUpdated = findById(id);
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setEmail(user.getEmail());
        //When Hibernate decides to flush the persistence context, the dirty checking mechanism will detect the change and perform the required SQL UPDATE statement.
    }

    @Override
    public void delete(long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query=entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

}
