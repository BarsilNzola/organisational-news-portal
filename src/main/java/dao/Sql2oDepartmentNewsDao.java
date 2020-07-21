package dao;

import models.DepartmentNews;
import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentNewsDao implements DepartmentNewsDao {

    private final Sql2o sql2o;
    public Sql2oDepartmentNewsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(DepartmentNews departmentNews) {
        String sql = "INSERT INTO department_news(post,postedBy) VALUES(:post,:postedBy)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(departmentNews)
                    .executeUpdate()
                    .getKey();
            departmentNews.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<DepartmentNews> getDepartmentNews() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM department_news")
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public void addDepartmentNewsToDepartment(DepartmentNews departmentNews, Department department) {
        String sql = "INSERT INTO departments_departmentNews(department_id,departmentNews_id) VALUES(:departmentId,:departmentNewsId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("departmentNewsId", departmentNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    

    @Override
    public DepartmentNews findById(int id) {
        String sql = "SELECT * FROM department_news WHERE id =:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from department_news WHERE id = :id";
        String deleteJoin = "DELETE from departmentId_departmentId WHERE department_id = :department_id";
        String deleteJoindepartment = "DELETE from employeeId_departmentId WHERE employee_id = :employee_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
//            con.createQuery(deleteJoin)
//                    .addParameter("department_id", id)
//                    .executeUpdate();
//            con.createQuery(deleteJoin)
//                    .addParameter("user_id", id)
//                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from department_news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
