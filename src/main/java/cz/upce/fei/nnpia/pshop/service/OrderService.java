package cz.upce.fei.nnpia.pshop.service;

import cz.upce.fei.nnpia.pshop.entity.Order;
import cz.upce.fei.nnpia.pshop.entity.connection.OrderItem;
import cz.upce.fei.nnpia.pshop.repository.OrderRepository;
import cz.upce.fei.nnpia.pshop.repository.connection.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements ServiceI<Order> {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public List<Order> getByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public double getOrderPrice(Long orderId, Double tax) {
        Order order = getById(orderId);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);

        double orderPrice = order.getShippingPrice() + order.getPaymentPrice();

        for (OrderItem item : orderItems) {
            orderPrice += item.getItem().getPrice() * item.getCount();
        }

        return orderPrice * tax;
    }
}
