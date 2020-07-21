package dao;

import java.util.List;
import models.Department;

public interface DepartmentDao {

    //create
    void add (Department department);

    //read
    List<Department> getAll();
    Department findById(int id);

    //update
    void update(String name, String description, int noOfEmployees);

    //delete
    void deleteById(int id);
    void clearAll();
}
