package controller;

import cz.upce.fei.nnpia.pshop.controller.UserController;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.repository.UserRepository;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import cz.upce.fei.nnpia.pshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;

    @Mock private UserService userService;

    @Mock private JwtService jwtService;

    @Mock private UserRepository userRepository;

    @Mock private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, jwtService, userRepository, passwordEncoder);
    }

    @Test
    void testGetUserById_Authenticated() {
        Long userId = 1L;
        String token = "Bearer validToken";
        User user = new User();
        user.setId(userId);
        user.setUsername("username");
        when(userService.getById(userId)).thenReturn(user);
        when(jwtService.extractUsername("validToken")).thenReturn(user.getUsername());

        ResponseEntity<?> responseEntity = userController.getUserById(userId, token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetUserById_Forbidden() {
        Long userId = 1L;
        String token = "Bearer invalidToken";
        User user = new User();
        user.setId(userId);
        user.setUsername("username");
        when(userService.getById(userId)).thenReturn(user);
        when(jwtService.extractUsername("invalidToken")).thenReturn("anotherUsername");

        ResponseEntity<?> responseEntity = userController.getUserById(userId, token);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
}
