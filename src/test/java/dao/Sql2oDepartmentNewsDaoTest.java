package dao;

import models.Department;
import models.DepartmentNews;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsDaoTest {

    private Connection conn;
    private Sql2oDepartmentNewsDao departmentNewsDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public DepartmentNews setupDepartmentNews(){
        DepartmentNews departmentNews = new DepartmentNews("client added new features","Barsil");
        departmentNewsDao.add(departmentNews);
        return departmentNews;
    }
    public DepartmentNews setupAnotherDepartmentNews(){
        DepartmentNews departmentNews = new DepartmentNews("salary decreament","Jackqueen");
        departmentNewsDao.add(departmentNews);
        return departmentNews;
    }

    public Department setUpDepartment(){
        Department department = new Department("Hospitality","Found in love",24);
        departmentDao.add(department);
        return department;
    }
    @Test
    public void addingDepartmentNewsSetsId() throws Exception {
        DepartmentNews testDepartmentNews = setupAnotherDepartmentNews();
        assertEquals(1, testDepartmentNews.getId());
    }
    @Test
    public void getAll() throws Exception {
        DepartmentNews departmentNews1 = setupDepartmentNews();
        DepartmentNews departmentNews2 = setupAnotherDepartmentNews();
        assertEquals(2, departmentNewsDao.getDepartmentNews().size());
    }


    @Test
    public void deleteById() throws Exception {
        DepartmentNews testDepartmentNews = setupDepartmentNews();
        DepartmentNews otherDepartmentNews = setupAnotherDepartmentNews();
        assertEquals(2, departmentNewsDao.getDepartmentNews().size());
        departmentNewsDao.deleteById(testDepartmentNews.getId());
        assertEquals(1, departmentNewsDao.getDepartmentNews().size());
    }
    @Test
    public void clearAll() throws Exception {
        DepartmentNews testDepartmentNews = setupDepartmentNews();
        DepartmentNews otherDepartmentNews = setupAnotherDepartmentNews();
        departmentNewsDao.clearAll();
        assertEquals(0, departmentNewsDao.getDepartmentNews().size());
    }
}