package dao;

import java.util.List;
import models.Department;
import models.DepartmentNews;

public interface DepartmentDao {

    //create
    void add (Department department);
    void addDepartmentNewsToDepartment (DepartmentNews departmentnews, Department department);

    //read
    List<Department> getDepartments();
    List<DepartmentNews>getAllDepartmentNewsByDepartment(int departmentId);
    Department findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
