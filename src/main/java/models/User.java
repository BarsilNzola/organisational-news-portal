package models;

public class User {
    private String name;
    private String position;
    private String role;
    private int departmentId;

    public User(String name, String position, String role, int departmentId) {
        this.name = name;
        this.position = position;
        this.role = role;
        this.departmentId = departmentId;
    }
}
