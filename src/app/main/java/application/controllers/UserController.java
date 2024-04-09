package application.controllers;

import application.entities.AppUser;
import application.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping()
    public List<AppUser> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(
            @PathVariable
            Long id) {
        AppUser appUser = service.getById(id);
        if (appUser != null) {
            return ResponseEntity.ok(appUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createUser(
            @RequestBody
            @Valid
            AppUser appUser) {
        service.create(appUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            AppUser appUser) {
        service.update(appUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable
            Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

