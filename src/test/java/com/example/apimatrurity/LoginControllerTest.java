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

// Other imports remain the same

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
}