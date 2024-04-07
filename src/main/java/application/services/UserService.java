package application.services;

import application.entities.AppUser;
import application.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceI<AppUser> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AppUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getById(Long id) {
        Optional<AppUser> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public AppUser create(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public AppUser update(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
