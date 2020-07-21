package dao;

import models.DepartmentNews;
import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {

    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments(name,description,noOfEmployees) VALUES(:name,:description,:noOfEmployees)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Department> getDepartments() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public void addDepartmentNewsToDepartment(DepartmentNews departmentNews, Department department) {
        String sql = "INSERT INTO departments_departmentNews(department_id,departmentNews_id) VALUES (:departmentId,:departmentNewsId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("departmentNewsId", departmentNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<DepartmentNews> getAllDepartmentNewsByDepartment(int department_id) {
        ArrayList<DepartmentNews> departmentNews = new ArrayList<>();
        String joinQuery = "SELECT departmentNews_id FROM departments_departmentNews WHERE department_id =:department_id";
        try (Connection con = sql2o.open()) {
            List<Integer> allDepartmentNewsIds = con.createQuery(joinQuery)
                    .addParameter("department_id", department_id)
                    .executeAndFetch(Integer.class);
            for (Integer departmentNewsId : allDepartmentNewsIds) {
                String departmentNewsQuery = "SELECT * FROM department_news WHERE id =:departmentNews_id";
                departmentNews.add(con.createQuery(departmentNewsQuery)
                        .addParameter("departmentNews_id", departmentNewsId)
                        .executeAndFetchFirst(DepartmentNews.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return departmentNews;
    }

    @Override
    public Department findById(int department_id) {
        String sql = "SELECT * FROM departments WHERE id =:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", department_id)
                    .executeAndFetchFirst(Department.class);
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String deleteJoin = "DELETE from departmentId_departmentNewsId WHERE departmentNews_id = :departmentNews_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("departmentNews_id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}