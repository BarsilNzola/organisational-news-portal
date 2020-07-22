package dao;

import models.Department;
import models.DepartmentNews;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsDaoTest {

    private static Connection conn;
    private static Sql2oDepartmentNewsDao departmentNewsDao;
    private static Sql2oDepartmentDao departmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organizational_news_test";
        Sql2o sql2o = new Sql2o(connectionString, "barsil", "madboysent7042");
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll();
        departmentNewsDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
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