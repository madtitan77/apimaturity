import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.example.apimaturity.LoginRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.mock.mockito.MockBean;



@SpringBootTest(classes = com.example.apimaturity.Application.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Use MockBean to mock the AuthenticationManager
    private AuthenticationManager authenticationManager;

    @Test
    public void authenticateUserTest() throws Exception {
        // Setup test data
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        // Convert to JSON
        String loginRequestJson = "{\"username\":\"user\",\"password\":\"pass\"}";

        // Setup mock authentication behavior
        Authentication auth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(auth); // Ensure this returns a non-null Authentication object

        // Perform POST request and assert response
        mockMvc.perform(post("/api/apimaturity/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson))
                .andExpect(status().isForbidden());

        // Optional: Verify interactions or assert response content
    }


   /*  @Test
    public void createUserAndLoginTest() throws Exception {
        
        // Setup test data
        LoginRequest createUserRequest = new LoginRequest();
        createUserRequest.setUsername("newUser");
        createUserRequest.setPassword("newPass");
        // Convert to JSON
        String createUserRequestJson = "{\"username\":\"newUser\",\"password\":\"newPass\"}";
        // Setup mock authentication behavior
        Authentication createAuth = new UsernamePasswordAuthenticationToken(createUserRequest.getUsername(), createUserRequest.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(createAuth); // Ensure this returns a non-null Authentication object
        // Perform POST request to create user
        mockMvc.perform(post("/api/apimaturity/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserRequestJson))
                .andExpect(status().isOk());
        
        //getAuth();

        
    }


    private void getAuth() throws Exception {
        // Setup login test data
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("newUser");
        loginRequest.setPassword("newPass");
        // Convert to JSON
        String loginRequestJson = "{\"username\":\"newUser\",\"password\":\"newPass\"}";
        // Setup mock authentication behavior
        Authentication auth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(auth); // Ensure this returns a non-null Authentication object
        // Perform POST request and assert response
        mockMvc.perform(post("/api/apimaturity/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson))
                .andExpect(status().isOk());
        // Optional: Verify interactions or assert response content
    }*/
}