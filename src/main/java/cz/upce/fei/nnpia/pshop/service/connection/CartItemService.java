package cz.upce.fei.nnpia.pshop.service.connection;

import cz.upce.fei.nnpia.pshop.entity.connection.CartItem;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import cz.upce.fei.nnpia.pshop.repository.connection.CartItemRepository;
import cz.upce.fei.nnpia.pshop.service.ServiceI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService implements ServiceI<CartItem> {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getById(Long id) {
        Optional<CartItem> item = cartItemRepository.findById(id);
        return item.orElse(null);
    }

    public List<CartItem> getByCartId(Long id) {
        return cartItemRepository.findByCartId(id);
    }

    public CartItem getByCartIdAndItemIdAndType(Long cartId, Long itemId, ItemE itemType) {
        Optional<CartItem> item = cartItemRepository.findByCartIdAndItemIdAndItemType(cartId, itemId, itemType);
        return item.orElse(null);
    }

    @Override
    public CartItem create(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem update(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
