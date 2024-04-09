package application.services;

import application.entities.UserCart;
import application.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ServiceI<UserCart> {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<UserCart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public UserCart getById(Long id) {
        Optional<UserCart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    @Override
    public UserCart create(UserCart userCart) {
        return cartRepository.save(userCart);
    }

    @Override
    public UserCart update(UserCart userCart) {
        return cartRepository.save(userCart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
