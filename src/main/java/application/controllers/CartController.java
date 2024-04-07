package application.controllers;

import application.entities.Cart;
import application.entities.User;
import application.services.CartService;
import application.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService service;

    @GetMapping()
    public List<Cart> getAllCarts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(
            @PathVariable
            Long id) {
        Cart cart = service.getById(id);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createCart(
            @RequestBody
            @Valid
            Cart cart) {
        service.create(cart);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            Cart cart) {
        service.update(cart);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable
            Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

