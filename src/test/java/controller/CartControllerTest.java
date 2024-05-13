package controller;

import cz.upce.fei.nnpia.pshop.controller.CartController;
import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import cz.upce.fei.nnpia.pshop.service.CartService;
import cz.upce.fei.nnpia.pshop.service.UserService;
import cz.upce.fei.nnpia.pshop.service.connection.CartItemService;
import cz.upce.fei.nnpia.pshop.service.items.CameraService;
import cz.upce.fei.nnpia.pshop.service.items.LensService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    private CartController cartController;

    @Mock private CartService cartService;

    @Mock private UserService userService;

    @Mock private CameraService cameraService;

    @Mock private LensService lensService;

    @Mock private CartItemService cartItemService;

    @Mock private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartController = new CartController(cartService, userService, cameraService, lensService, cartItemService, jwtService);
    }

    @Test
    void testGetCartByUserId() {
        Long userId = 1L;
        String token = "Bearer validToken";
        User user = new User();
        user.setId(userId);
        user.setUsername("username");
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        when(userService.getById(userId)).thenReturn(user);
        when(jwtService.extractUsername("validToken")).thenReturn(user.getUsername());
        when(cartService.getByUserId(userId)).thenReturn(cart);
        when(cartItemService.getByCartId(cart.getId())).thenReturn(Collections.emptyList());

        ResponseEntity<?> responseEntity = cartController.getCartById(userId, token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
