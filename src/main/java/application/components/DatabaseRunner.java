package application.components;

import application.entities.AppUser;
import application.repositories.UserRepository;
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
    public void run(ApplicationArguments args) throws Exception {
        AppUser appUser = new AppUser();
        appUser.setName("Jan");
        appUser.setSurname("Stehno");
        appUser.setUsername("janstehno");
        appUser.setPassword("password");
        appUser.setActive(true);
        userRepository.save(appUser);
    }
}

