package com.example.projectcalculationtool.models.DTO;

public class UserAssignDTO {
    private int userID;
    private String fullName;

    public UserAssignDTO() {}

    public UserAssignDTO(int userID, String fullName) {
        this.userID = userID;
        this.fullName = fullName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
