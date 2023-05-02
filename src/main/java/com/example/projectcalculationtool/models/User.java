package com.example.projectcalculationtool.models;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int roleID;

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleID=" + roleID +
                '}';
    }

    public User(int userID, String firstName, String lastName, String email, String password, int roleID) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
    }

    public int getUserID() { return userID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getRoleID() { return roleID; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRoleID(int roleID) { this.roleID = roleID; }
}
