import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.example.apimaturity.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public void successfulLoginTest() throws Exception {    
        // Setup test data for successful login
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("validUser");
        loginRequest.setPassword("validPass");

        // Serialize loginRequest to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

        // Mock successful authentication
        Authentication auth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        // Perform POST request and expect OK status
        mockMvc.perform(post("/api/apimaturity/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson))
                .andExpect(status().isOk());
    }


   @Test
    public void unsuccessfulLoginTest() throws Exception {
        // Setup test data for unsuccessful login
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("invalidUser");
        loginRequest.setPassword("invalidPass");

        // Serialize loginRequest to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

        // Mock unsuccessful authentication by throwing BadCredentialsException
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Bad credentials"));

        // Perform POST request and expect Unauthorized status
        mockMvc.perform(post("/api/apimaturity/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson))
                .andExpect(status().isUnauthorized());
    }
}