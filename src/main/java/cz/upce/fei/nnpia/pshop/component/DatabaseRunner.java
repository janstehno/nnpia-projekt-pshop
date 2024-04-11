package cz.upce.fei.nnpia.pshop.component;

import cz.upce.fei.nnpia.pshop.entity.Role;
import cz.upce.fei.nnpia.pshop.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseRunner implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        roleRepository.save(Role.builder().name("ADMIN").build());
        roleRepository.save(Role.builder().name("USER").build());
    }
}

