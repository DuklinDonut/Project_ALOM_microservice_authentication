package fr.lille.alom.authentication_server;

import java.util.UUID;

public class UserDTO {
    private String id;
    private String username;
    private String password;

    // Empty constructor
    public UserDTO() {
        // No ID generation here to allow flexibility in usage
    }

    // Constructor to automatically assign a unique ID
    public UserDTO(boolean generateId) {
        if (generateId) {
            this.id = UUID.randomUUID().toString();
        }
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
