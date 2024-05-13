package cz.upce.fei.nnpia.pshop.controller;

import cz.upce.fei.nnpia.pshop.dto.MinimalOrderItemDTO;
import cz.upce.fei.nnpia.pshop.dto.OrderItemDTO;
import cz.upce.fei.nnpia.pshop.dto.MinimalOrderDTO;
import cz.upce.fei.nnpia.pshop.dto.OrderDTO;
import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.entity.Order;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.entity.connection.OrderItem;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import cz.upce.fei.nnpia.pshop.entity.enums.OrderE;
import cz.upce.fei.nnpia.pshop.entity.items.Camera;
import cz.upce.fei.nnpia.pshop.entity.items.Item;
import cz.upce.fei.nnpia.pshop.entity.items.Lens;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import cz.upce.fei.nnpia.pshop.service.CartService;
import cz.upce.fei.nnpia.pshop.service.OrderService;
import cz.upce.fei.nnpia.pshop.service.UserService;
import cz.upce.fei.nnpia.pshop.service.connection.CartItemService;
import cz.upce.fei.nnpia.pshop.service.connection.OrderItemService;
import cz.upce.fei.nnpia.pshop.service.items.CameraService;
import cz.upce.fei.nnpia.pshop.service.items.LensService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    private final CartService cartService;

    private final UserService userService;

    private final CameraService cameraService;

    private final LensService lensService;

    private final CartItemService cartItemService;

    private final OrderItemService orderItemService;

    private final JwtService jwtService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<MinimalOrderDTO>> getCartById(
            @PathVariable
            Long userId,
            @RequestHeader("Authorization")
            String token) {
        User user = userService.getById(userId);
        String username = jwtService.extractUsername(token.substring(7));
        if (user != null && username.equals(user.getUsername())) {
            List<Order> orders = service.getByUserId(userId);
            orders.sort(Comparator.comparing(Order::getCreation_date));
            List<MinimalOrderDTO> ordersDTO = new ArrayList<>();
            for (Order order : orders) {
                List<MinimalOrderItemDTO> minimalOrderItemsDTO = new ArrayList<>();
                List<OrderItem> orderItems = orderItemService.getByOrderId(order.getId());
                for (OrderItem orderItem : orderItems) {
                    Item item = null;
                    if (orderItem.getItemType().equals(ItemE.CAMERA)) {
                        item = cameraService.getById(orderItem.getItem().getId());
                    } else if (orderItem.getItemType().equals(ItemE.LENS)) {
                        item = lensService.getById(orderItem.getItem().getId());
                    }
                    minimalOrderItemsDTO.add(new MinimalOrderItemDTO(orderItem.getItem().getId(), item.getPrice(), orderItem.getCount()));
                }
                ordersDTO.add(new MinimalOrderDTO(order.getId(), order.getShippingPrice(), order.getPaymentPrice(), minimalOrderItemsDTO, order.getState()));
            }
            return ResponseEntity.ok(ordersDTO);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Void> addItem(
            @PathVariable
            Long userId,
            @RequestBody
            @Valid
            OrderDTO orderDTO,
            @RequestHeader("Authorization")
            String token) {
        User user = userService.getById(userId);
        String username = jwtService.extractUsername(token.substring(7));
        if (user != null && username.equals(user.getUsername())) {
            Cart cart = cartService.getByUserId(userId);
            cartItemService.deleteAllByCartId(cart.getId());
            Order order = Order.builder()
                               .creation_date(LocalDateTime.now())
                               .state(OrderE.CREATED)
                               .street(orderDTO.getAddress().getStreet())
                               .city(orderDTO.getAddress().getCity())
                               .zipCode(orderDTO.getAddress().getZipCode())
                               .phone(orderDTO.getAddress().getPhone())
                               .shippingMethod(orderDTO.getShippingMethod())
                               .paymentMethod(orderDTO.getPaymentMethod())
                               .shippingPrice(orderDTO.getShippingPrice())
                               .paymentPrice(orderDTO.getPaymentPrice())
                               .user(user)
                               .build();
            service.create(order);
            for (OrderItemDTO cartItem : orderDTO.getItems()) {
                Item item = null;
                if (cartItem.getType().equals(ItemE.CAMERA)) {
                    item = cameraService.getById(cartItem.getId());
                    item.setInStorage(item.getInStorage() - cartItem.getCount());
                    cameraService.update((Camera) item);
                } else if (cartItem.getType().equals(ItemE.LENS)) {
                    item = lensService.getById(cartItem.getId());
                    item.setInStorage(item.getInStorage() - cartItem.getCount());
                    lensService.update((Lens) item);
                }
                if (item != null) {
                    OrderItem orderItem = OrderItem.builder().itemType(cartItem.getType()).order(order).item(item).count(cartItem.getCount()).build();
                    orderItemService.create(orderItem);
                }
            }
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

}

