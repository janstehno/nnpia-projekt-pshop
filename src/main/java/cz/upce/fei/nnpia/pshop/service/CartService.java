package cz.upce.fei.nnpia.pshop.service;

import cz.upce.fei.nnpia.pshop.entity.ShoppingCart;
import cz.upce.fei.nnpia.pshop.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ServiceI<ShoppingCart> {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<ShoppingCart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public ShoppingCart getById(Long id) {
        Optional<ShoppingCart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return cartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return cartRepository.save(shoppingCart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
