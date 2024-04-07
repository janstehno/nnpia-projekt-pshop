package application.services;

import application.entities.ItemOrder;
import application.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ServiceI<ItemOrder> {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<ItemOrder> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public ItemOrder getById(Long id) {
        Optional<ItemOrder> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public ItemOrder create(ItemOrder itemOrder) {
        return orderRepository.save(itemOrder);
    }

    @Override
    public ItemOrder update(ItemOrder itemOrder) {
        return orderRepository.save(itemOrder);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
