package models;

public class Department {
    private String name;
    private String description;
    private int noOfEmployees;

    public Department(String name, String description, int noOfEmployees) {
        this.name = name;
        this.description = description;
        this.noOfEmployees = noOfEmployees;
    }
}
