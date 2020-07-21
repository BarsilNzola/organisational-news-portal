package dao;

import models.News;
import models.User;

import java.util.List;

public interface UserDao {

    //create
    void add (User user);

    //read
    List<User> getAll();
    News findById(int id);

    //update
    void update(String name, String position, String role, int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();
}
