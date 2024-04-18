package cz.upce.fei.nnpia.pshop.controller;

import cz.upce.fei.nnpia.pshop.dto.UserNameDTO;
import cz.upce.fei.nnpia.pshop.dto.UserPasswordDTO;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.exception.CustomExceptionHandler;
import cz.upce.fei.nnpia.pshop.repository.UserRepository;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import cz.upce.fei.nnpia.pshop.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    private final JwtService jwtService;

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable
            Long id,
            @RequestHeader("Authorization")
            String token) {
        User user = service.getById(id);
        String username = jwtService.extractUsername(token.substring(7));
        if (user != null && username.equals(user.getUsername())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            UserNameDTO data,
            @RequestHeader("Authorization")
            String token) {
        User user = service.getById(id);
        String username = jwtService.extractUsername(token.substring(7));
        final Optional<User> foundByEmail = repository.findByEmail(data.getEmail());
        if (foundByEmail.isPresent() && !foundByEmail.orElse(null).getId().equals(id)) {
            return new ResponseEntity<>(new CustomExceptionHandler.EmailAlreadyExistsException("EMAIL_EXISTS"), HttpStatus.FORBIDDEN);
        }
        if (user != null && username.equals(user.getUsername())) {
            user.setFirstname(data.getFirstname());
            user.setLastname(data.getLastname());
            user.setEmail(data.getEmail());
            user.setUpdate_date(data.getUpdateDate());
            service.update(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/password/{id}")
    public ResponseEntity<Void> updateUserPassword(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            UserPasswordDTO data,
            @RequestHeader("Authorization")
            String token) {
        User user = service.getById(id);
        String username = jwtService.extractUsername(token.substring(7));
        if (user != null && username.equals(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(data.getPassword()));
            user.setUpdate_date(data.getUpdateDate());
            service.update(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable
            Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

