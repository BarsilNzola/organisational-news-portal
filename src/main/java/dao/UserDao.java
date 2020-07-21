package dao;

import models.News;
import models.User;

import java.util.List;

public interface UserDao {

    //create
    void add (User user);

    //read
    List<User> getUsers();
    List<User> getAllUsersFromADepartment(int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();
}
