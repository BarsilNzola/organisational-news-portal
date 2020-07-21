package dao;

import models.DepartmentNews;
import models.Department;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
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