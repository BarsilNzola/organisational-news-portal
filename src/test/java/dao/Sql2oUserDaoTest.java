package dao;

import models.Department;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private Connection conn;
    private Sql2oDepartmentNewsDao departmentNewsDao;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oUserDao userDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
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