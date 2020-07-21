package dao;

import models.DepartmentNews;
import models.Department;
import models.User;

import java.util.List;

public interface DepartmentNewsDao {

    void add(DepartmentNews departmentNews);

    void addDepartmentNewsToDepartment(DepartmentNews departmentNews , Department department);

    List<DepartmentNews> getDepartmentNews();

    DepartmentNews findById(int id);

    void deleteById(int id);

    void clearAll();
}
