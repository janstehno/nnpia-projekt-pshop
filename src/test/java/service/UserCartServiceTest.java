package service;

import cz.upce.fei.nnpia.pshop.App;
import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.service.CartService;
import cz.upce.fei.nnpia.pshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = App.class)
class UserCartServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Test
    void testCreateUserWithCart() {
        User user = new User();
        user.setUsername("user_with_cart");
        user.setFirstname("Firstname");
        user.setLastname("Lastname");
        user.setPassword("password");
        user.setEmail("user@pshop.com");

        userService.create(user);

        Cart cart = cartService.getByUserId(user.getId());

        assertEquals(user.getId(), cart.getUser().getId());
    }
}
