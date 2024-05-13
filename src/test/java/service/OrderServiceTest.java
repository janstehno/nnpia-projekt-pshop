package service;

import cz.upce.fei.nnpia.pshop.entity.Order;
import cz.upce.fei.nnpia.pshop.entity.connection.OrderItem;
import cz.upce.fei.nnpia.pshop.entity.items.Camera;
import cz.upce.fei.nnpia.pshop.entity.items.Lens;
import cz.upce.fei.nnpia.pshop.repository.OrderRepository;
import cz.upce.fei.nnpia.pshop.repository.connection.OrderItemRepository;
import cz.upce.fei.nnpia.pshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;

    @Mock private OrderRepository orderRepository;

    @Mock private OrderItemRepository orderItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, orderItemRepository);
    }

    @Test
    void testCalculateOrderPrice() {
        double tax = 0.21;

        Camera camera = new Camera();
        camera.setId(1L);
        camera.setPrice(36490.0);

        Lens lens = new Lens();
        lens.setId(2L);
        lens.setPrice(23290.0);

        Order order = new Order();
        order.setId(1L);
        order.setShippingPrice(79.0);
        order.setPaymentPrice(40.0);

        OrderItem item1 = new OrderItem();
        item1.setId(1L);
        item1.setOrder(order);
        item1.setItem(camera);
        item1.setItemType(camera.getItemType());
        item1.setCount(2);

        OrderItem item2 = new OrderItem();
        item2.setId(2L);
        item2.setOrder(order);
        item2.setItem(lens);
        item2.setItemType(lens.getItemType());
        item2.setCount(1);

        List<OrderItem> orderItems = new ArrayList<>(Arrays.asList(item1, item2));

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderItemRepository.findAllByOrderId(1L)).thenReturn(orderItems);

        double expectedPrice = ((camera.getPrice() * item1.getCount()) + (lens.getPrice() * item2.getCount()) + order.getShippingPrice() + order.getPaymentPrice()) * tax;

        assertEquals(expectedPrice, orderService.getOrderPrice(order.getId(), tax));
    }
}
