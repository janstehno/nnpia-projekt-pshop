package cz.upce.fei.nnpia.pshop.controller;

import cz.upce.fei.nnpia.pshop.entity.ShoppingCart;
import cz.upce.fei.nnpia.pshop.service.CartService;
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
    public List<ShoppingCart> getAllCarts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(
            @PathVariable
            Long id) {
        ShoppingCart shoppingCart = service.getById(id);
        if (shoppingCart != null) {
            return ResponseEntity.ok(shoppingCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createCart(
            @RequestBody
            @Valid
            ShoppingCart shoppingCart) {
        service.create(shoppingCart);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            ShoppingCart shoppingCart) {
        service.update(shoppingCart);
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

