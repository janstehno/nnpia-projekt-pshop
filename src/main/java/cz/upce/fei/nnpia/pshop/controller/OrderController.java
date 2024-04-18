package cz.upce.fei.nnpia.pshop.controller;

import cz.upce.fei.nnpia.pshop.entity.Order;
import cz.upce.fei.nnpia.pshop.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @GetMapping()
    public List<Order> getAllOrders() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable
            Long id) {
        Order order = service.getById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

