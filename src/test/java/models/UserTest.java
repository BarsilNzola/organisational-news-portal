package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getNameReturnsCorrectName() {
        User testUser = setUpUser();
        assertEquals("Barsil", testUser.getName());
    }

    @Test
    public void setNameSetsCorrectName() {
        User testUser = setUpUser();
        testUser.setName("Jackqueen");
        assertNotEquals("Barsil", testUser.getName());
    }

    @Test
    public void getPositionReturnsCorrectPosition() {
        User testUser = setUpUser();
        assertEquals("junior developer", testUser.getPosition());
    }

    @Test
    public void setPositionSetsCorrectPosition() {
        User testUser = setUpUser();
        testUser.setPosition("assistant hr");
        assertNotEquals("junior development", testUser.getPosition());
    }

    @Test
    public void getRoleReturnsCorrectRole() {
        User testUser = setUpUser();
        assertEquals("app development", testUser.getRole());
    }

    @Test
    public void setRoleSetsCorrectRole() {
        User testUser = setUpUser();
        testUser.setRole("employee management");
        assertNotEquals("app development", testUser.getRole());
    }

    @Test
    public void getDepartmentIdReturnsCorrectId() {
        User testUser = setUpUser();
        assertEquals(3, testUser.getDepartmentId());
    }

    @Test
    public void setDepartmentIdSetsCorrectId() {
        User testUser = setUpUser();
        testUser.setDepartmentId(4);
        assertNotEquals(4, testUser.getDepartmentId());
    }

    public User setUpUser() {
        return new User("Barsil", "junior developer", "app development", 3);
    }
}