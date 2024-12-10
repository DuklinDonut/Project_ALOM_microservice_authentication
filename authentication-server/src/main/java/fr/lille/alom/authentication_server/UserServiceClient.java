package fr.lille.alom.authentication_server;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceClient {

    private static final String USER_SERVICE_URL = "http://localhost:8082/user-service/api/user/list";

    public List<UserDTO> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        UserDTO[] users = restTemplate.getForObject(USER_SERVICE_URL, UserDTO[].class);
        return Arrays.asList(users);
    }
}
