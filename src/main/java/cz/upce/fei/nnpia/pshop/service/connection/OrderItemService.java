package cz.upce.fei.nnpia.pshop.service.connection;

import cz.upce.fei.nnpia.pshop.entity.connection.OrderItem;
import cz.upce.fei.nnpia.pshop.repository.connection.OrderItemRepository;
import cz.upce.fei.nnpia.pshop.service.ServiceI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemService implements ServiceI<OrderItem> {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getById(Long id) {
        Optional<OrderItem> order = orderItemRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public OrderItem create(OrderItem order) {
        return orderItemRepository.save(order);
    }

    @Override
    public OrderItem update(OrderItem order) {
        return orderItemRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
