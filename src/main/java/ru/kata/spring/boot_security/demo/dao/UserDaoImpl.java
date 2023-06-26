package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public User getUserByID(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUserByID(Long id) {

        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {

        entityManager.merge(user);
    }
}

