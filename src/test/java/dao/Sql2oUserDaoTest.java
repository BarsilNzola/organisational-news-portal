package dao;

import models.Department;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
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
        conn.close();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
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

    public Department setUpDepartment(){
        Department department = new Department("Human Resource", "employee management", 7);
        departmentDao.add(department);
        return department;
    }
    public Department setUpAnotherDepartment(){
        Department department = new Department("IT","Connectivity",6);
        departmentDao.add(department);
        return department;
    }
    public User setupUserForDepartment(Department department) {
        User user = new User("Barsil", "junior developer", "app development",department.getId());
        userDao.add(user);
        return user;
    }
    @Test
    public void addingUserSetsId() throws Exception {
        User testUser = setUpUser();
        assertEquals(1, testUser.getId());
    }
    @Test
    public void getAll() throws Exception {
        User user1 = setUpUser();
        User user2 = setUpAnotherUser();
        assertEquals(2, userDao.getUsers().size());
    }
    @Test
    public void getAllUsersByDepartment() throws Exception {
        Department testDepartment = setUpDepartment();
        Department otherDepartment = setUpDepartment(); //add in some extra data to see if it interferes
        User review1 = setupUserForDepartment(testDepartment);
        User review2 = setupUserForDepartment(testDepartment);
        User reviewForOtherDepartment = setupUserForDepartment(otherDepartment);
        assertEquals(2, userDao.getAllUsersFromADepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        User testUser = setUpUser();
        User otherUser = setUpAnotherUser();
        assertEquals(2, userDao.getUsers().size());
        userDao.deleteById(testUser.getId());
        assertEquals(1, userDao.getUsers().size());
    }
    @Test
    public void clearAll() throws Exception {
        User testUser = setUpUser();
        User otherUser = setUpAnotherUser();
        userDao.clearAll();
        assertEquals(0, userDao.getUsers().size());
    }
}