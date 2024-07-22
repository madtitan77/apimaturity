import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.apimaturity.model.Client;
import com.example.apimaturity.repository.ClientRepo;
import com.example.apimaturity.security.Role.RoleType;
import com.example.apimaturity.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientServiceImplTest {

    @Mock
    private ClientRepo clientRepo;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenAdminShouldReturnAllClients() {
        List<Client> allClients = Arrays.asList(new Client(), new Client());
        when(clientRepo.findAll()).thenReturn(allClients);

        List<Client> result = clientService.findClientsForUser("adminUser", RoleType.ADMIN.name());

        assertEquals(allClients.size(), result.size());
        verify(clientRepo, times(1)).findAll();
    }

    @Test
    public void whenUserShouldReturnClientsBelongingToUser() {
        List<Client> userClients = Arrays.asList(new Client());
        when(clientRepo.findByUser("user")).thenReturn(userClients);
        when(clientRepo.findClientsByUserPermission("user")).thenReturn(Arrays.asList());

        List<Client> result = clientService.findClientsForUser("user", RoleType.USER.name());

        assertEquals(userClients.size(), result.size());
        verify(clientRepo, times(1)).findByUser("user");
    }

    @Test
    public void whenUserWithNoDirectClientsShouldReturnPermittedClients() {
        List<Client> permittedClients = new ArrayList<>(Arrays.asList(new Client())); // Make the list modifiable
        when(clientRepo.findByUser("user")).thenReturn(new ArrayList<>()); // Return a modifiable empty list
        when(clientRepo.findClientsByUserPermission("user")).thenReturn(permittedClients);

        List<Client> result = clientService.findClientsForUser("user", RoleType.USER.name());

        assertEquals(permittedClients.size(), result.size());
        verify(clientRepo, times(1)).findByUser("user");
        verify(clientRepo, times(1)).findClientsByUserPermission("user");
    }
}