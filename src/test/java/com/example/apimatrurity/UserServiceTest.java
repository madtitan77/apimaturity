import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.apimaturity.dto.UserCreationDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.UserRepo;
import com.example.apimaturity.security.Role;
import com.example.apimaturity.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenCreatingUserWithoutRole_thenDefaultRoleIsUser() {
        // Setup
        UserCreationDTO dto = new UserCreationDTO();
        dto.setEmail("test@example.com");
        dto.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        // Action
        User result = userService.createUser(dto);

        // Assert
        assertEquals(Role.RoleType.USER, result.getRole());
    }
}