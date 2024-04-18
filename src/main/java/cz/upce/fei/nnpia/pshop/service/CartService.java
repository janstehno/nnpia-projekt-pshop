package cz.upce.fei.nnpia.pshop.service;

import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ServiceI<Cart> {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    public Cart getByUserId(Long id) {
        Optional<Cart> cart = cartRepository.findByUserId(id);
        return cart.orElse(null);
    }

    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
