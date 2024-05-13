package cz.upce.fei.nnpia.pshop.controller;

import cz.upce.fei.nnpia.pshop.dto.ItemDTO;
import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.entity.connection.CartItem;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import cz.upce.fei.nnpia.pshop.entity.items.Item;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import cz.upce.fei.nnpia.pshop.service.CartService;
import cz.upce.fei.nnpia.pshop.service.UserService;
import cz.upce.fei.nnpia.pshop.service.connection.CartItemService;
import cz.upce.fei.nnpia.pshop.service.items.CameraService;
import cz.upce.fei.nnpia.pshop.service.items.LensService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService service;

    private final UserService userService;

    private final CameraService cameraService;

    private final LensService lensService;

    private final CartItemService cartItemService;

    private final JwtService jwtService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartById(
            @PathVariable
            Long userId,
            @RequestHeader("Authorization")
            String token) {
        User user = userService.getById(userId);
        String username = jwtService.extractUsername(token.substring(7));
        Cart cart = service.getByUserId(userId);
        if (user != null && username.equals(user.getUsername())) {
            List<CartItem> cartItems = cartItemService.getByCartId(cart.getId());
            cartItems.sort(Comparator.comparing(o -> o.getItem().getName()));
            return ResponseEntity.ok(cartItems);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addItem(
            @PathVariable
            Long userId,
            @RequestBody
            @Valid
            ItemDTO itemDTO,
            @RequestHeader("Authorization")
            String token) {
        User user = userService.getById(userId);
        String username = jwtService.extractUsername(token.substring(7));
        Cart cart = service.getByUserId(userId);
        if (cart == null) {
            cart = Cart.builder().user(user).build();
            service.create(cart);
        }
        if (user != null && username.equals(user.getUsername())) {
            CartItem foundCartItem = cartItemService.getByCartIdAndItemIdAndType(cart.getId(), itemDTO.getId(), itemDTO.getType());
            if (foundCartItem != null) {
                foundCartItem.setCount(itemDTO.getCount());
                cartItemService.update(foundCartItem);
            } else {
                Item item = null;
                if (itemDTO.getType().equals(ItemE.CAMERA)) {
                    item = cameraService.getById(itemDTO.getId());
                } else if (itemDTO.getType().equals(ItemE.LENS)) {
                    item = lensService.getById(itemDTO.getId());
                }
                if (item != null) {
                    CartItem cartItem = CartItem.builder().itemType(itemDTO.getType()).cart(cart).item(item).count(itemDTO.getCount()).build();
                    cartItemService.create(cartItem);
                }
            }
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/remove/{userId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable
            Long userId,
            @RequestBody
            @Valid
            CartItem cartItem,
            @RequestHeader("Authorization")
            String token) {
        User user = userService.getById(userId);
        String username = jwtService.extractUsername(token.substring(7));
        if (user != null && username.equals(user.getUsername())) {
            CartItem foundCartItem = cartItemService.getById(cartItem.getId());
            if (foundCartItem != null) {
                cartItemService.deleteById(foundCartItem.getId());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(404).build();
            }
        } else {
            return ResponseEntity.status(403).build();
        }
    }

}

