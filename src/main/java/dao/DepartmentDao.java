package dao;

import java.util.List;
import models.Department;
import models.DepartmentNews;

public interface DepartmentDao {

    //create
    void add (Department department);
    void add (DepartmentNews departmentnews, Department department);

    //read
    List<Department> getAll();
    List<DepartmentNews>getAllDepartmentNewsbyDepartment(int departmentId);
    Department findById(int id);

    //update
    void update(String name, String description, int noOfEmployees);

    //delete
    void deleteById(int id);
    void clearAll();
}
