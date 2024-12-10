package fr.lille.alom.authentication_server;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthenticationService {
    //lsof -i :8080 | awk '/[1-9]/ {print $2}' | xargs kill -9
    private String storedUsername = "admin";
    private String storedHashedPassword = hashPassword("password");

    // Simple method to hash the password with SHA-256
    String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing SHA-256", e);
        }
    }
	 // Method to generate a unique token
	    public String generateToken(String id) {
	        String input = id + new Date().getTime();  // Concatenate ID and current timestamp
	
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hash = digest.digest(input.getBytes());
	            return Base64.getEncoder().encodeToString(hash);  // Encode as Base64 for compact representation
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Error generating token", e);
	        }
	    }

    // Method that handles authentication
    public String authenticate(String username, String password) {
        String hashedPassword = hashPassword(password);
        if (storedUsername.equals(username) && storedHashedPassword.equals(hashedPassword)) {
            return "Login successful";  // Login success message
        } else {
            return "Invalid credentials"; // Login failed message
        }
    }
}
