package com.example.projectcalculationtool.models;

public class Role {
    private int roleID;
    private String roleName;

    public Role() {}

    public Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public int getRoleID() { return roleID; }
    public String getRoleName() { return roleName; }

    public void setRoleID(int roleID) { this.roleID = roleID; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}