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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }
}
