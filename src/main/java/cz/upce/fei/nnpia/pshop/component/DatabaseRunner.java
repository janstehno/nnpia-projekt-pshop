package cz.upce.fei.nnpia.pshop.component;

import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements ApplicationRunner {

    private final UserRepository userRepository;

    public DatabaseRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        User user = new User();
        user.setName("Jan");
        user.setSurname("Stehno");
        user.setUsername("janstehno");
        user.setPassword("password");
        userRepository.save(user);
    }
}

