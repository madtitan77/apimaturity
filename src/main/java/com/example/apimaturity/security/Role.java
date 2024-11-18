package com.example.apimaturity.security;


public class Role {
    
    private RoleType roleType;

    public enum RoleType {
        ADMIN, USER;

        // If you need a custom string representation, add a new method
        public String getRoleName() {
            // Customize the string representation as needed
            return this.name(); // This uses the Enum's name() method
        }

        public static boolean isValidRole(String roleName) {
            for (RoleType role : RoleType.values()) {
                if (role.name().equals(roleName)) {
                    return true;
                }
            }
            return false;
        }
    }

    // No-argument constructor
    public Role() {
    }

    // Constructor that accepts a String
    public Role(String roleName) {
        if (RoleType.isValidRole(roleName)) {
            this.roleType = RoleType.valueOf(roleName);
        } else {
            throw new IllegalArgumentException("Invalid role: " + roleName);
        }
    }

    // Getter for roleType
    public RoleType getRoleType() {
        return roleType;
    }

    // Setter for roleType
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }


}