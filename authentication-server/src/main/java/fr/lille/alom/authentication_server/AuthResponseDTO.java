package fr.lille.alom.authentication_server;

public class AuthResponseDTO {
    private String username;
    private String hashedPassword;
    private String token;

    // Constructeur
    
    public AuthResponseDTO() {
        
    }
    
    public AuthResponseDTO(String username, String hashedPassword, String token) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.token = token;
    }

    // Getters
    public String getUsername() { return username; }
    public String getHashedPassword() { return hashedPassword; }
    public String getToken() { return token; }
}
