package fr.lille.alom.authentication_server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // Valider les identifiants
        if ("admin".equals(username) && "password".equals(password)) {
            // Générez le mot de passe haché et le token basé sur l'ID de l'utilisateur
            String hashedPassword = authenticationService.hashPassword(password);  // Appel à hashPassword via le service
            String token = authenticationService.generateToken(userDTO.getId());  // Appel à generateToken via le service

            AuthResponseDTO response = new AuthResponseDTO(username, hashedPassword, token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(null); // Authentification échouée
        }
    }
} */

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // Récupérer la liste des utilisateurs depuis user-service
        List<UserDTO> users = userServiceClient.getUsers();

        // Vérifier si les identifiants correspondent
        for (UserDTO user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Générer le mot de passe haché et le token basé sur l'ID de l'utilisateur
                String hashedPassword = authenticationService.hashPassword(password);
                String token = authenticationService.generateToken(user.getId());

                AuthResponseDTO response = new AuthResponseDTO(username, hashedPassword, token);
                return ResponseEntity.ok(response);
            }
        }

        // Retourner une erreur si aucun utilisateur ne correspond
        return ResponseEntity.status(401).body(null);
    }
}

