package application.controllers;

import application.entities.UserCart;
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
    public List<UserCart> getAllCarts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCart> getCartById(
            @PathVariable
            Long id) {
        UserCart userCart = service.getById(id);
        if (userCart != null) {
            return ResponseEntity.ok(userCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createCart(
            @RequestBody
            @Valid
            UserCart userCart) {
        service.create(userCart);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            UserCart userCart) {
        service.update(userCart);
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

