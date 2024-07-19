package com.example.apimaturity.security;


public class Role {
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
}