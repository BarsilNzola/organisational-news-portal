package models;

import org.junit.*;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void getNameReturnsCorrectName() {
        Department testDepartment = setupDepartment();
        assertEquals("Human Resource", testDepartment.getName());
    }

    @Test
    public void setNameSetsCorrectName() {
        Department testDepartment = setupDepartment();
        testDepartment.setName("Finance");
        assertNotEquals("Human Resource", testDepartment.getName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() {
        Department testDepartment = setupDepartment();
        assertEquals("employee management", testDepartment.getDescription());
    }

    @Test
    public void setDescriptionSetsCorrectDescription() {
        Department testDepartment = setupDepartment();
        testDepartment.setDescription("financial management");
        assertNotEquals("employee management", testDepartment.getDescription());
    }

    @Test
    public void getNoOfEmployeesGetsCorrectNumber() {
        Department testDepartment = setupDepartment();
        assertEquals(7, testDepartment.getNoOfEmployees());
    }

    @Test
    public void setNoOfEmployeesSetsCorrectNumber() {
        Department testDepartment = setupDepartment();
        testDepartment.setNoOfEmployees(8);
        assertNotEquals(7,testDepartment.getNoOfEmployees());
    }

    public Department setupDepartment () {
        return new Department("Human Resource", "employee management", 7);
    }
}