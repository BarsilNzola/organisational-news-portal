package dao;

import models.DepartmentNews;
import models.Department;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentNewsDao departmentNewsDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oUserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organizational_news_test";
        Sql2o sql2o = new Sql2o(connectionString, "barsil", "madboysent7042");
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll();
        departmentNewsDao.clearAll();
        userDao.clearAll();
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

    public Department setUpAnotherDepartment(){
        Department department = new Department("Finance","Trust us with your future",30);
        departmentDao.add(department);
        return department;
    }

    public User setUpUser(){
        User user = new User("Barsil", "junior developer", "app development", 3);
        userDao.add(user);
        return user;
    }

    public User setUpAnotherUser(){
        User user = new User("Jackqueen","Assistant HR", "employee management", 2);
        userDao.add(user);
        return user;
    }

    @Test
    public void setUpDepartment_getsId(){
        Department department = setUpDepartment();
        assertEquals(1,department.getId());
    }

    @Test
    public void getAll() throws Exception{
        Department department = setUpDepartment();
        Department department1 = setUpAnotherDepartment();
        assertEquals(2,departmentDao.getDepartments().size());
    }

    @Test
    public void getAllDepartmentNewsByDepartment() throws Exception {
        DepartmentNews departmentNews = setupDepartmentNews();
        departmentNewsDao.add(departmentNews);

        DepartmentNews departmentNews1 = setupAnotherDepartmentNews();
        departmentNewsDao.add(departmentNews1);

        Department testDepartment = setUpDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addDepartmentNewsToDepartment(departmentNews,testDepartment);
        departmentDao.addDepartmentNewsToDepartment(departmentNews1,testDepartment);

        DepartmentNews[] departmentsNews = {departmentNews,departmentNews1};
        assertEquals(Arrays.asList(departmentsNews), departmentDao.getAllDepartmentNewsByDepartment(testDepartment.getId()));
    }

}