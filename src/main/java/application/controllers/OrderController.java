package application.controllers;

import application.entities.ItemOrder;
import application.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @GetMapping()
    public List<ItemOrder> getAllOrders() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemOrder> getOrderById(
            @PathVariable
            Long id) {
        ItemOrder itemOrder = service.getById(id);
        if (itemOrder != null) {
            return ResponseEntity.ok(itemOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createOrder(
            @RequestBody
            @Valid
            ItemOrder itemOrder) {
        service.create(itemOrder);
        return ResponseEntity.noContent().build();
    }

}

