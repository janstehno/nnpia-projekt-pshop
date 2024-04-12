package cz.upce.fei.nnpia.pshop.security;

import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.exception.CustomExceptionHandler;
import cz.upce.fei.nnpia.pshop.repository.RoleRepository;
import cz.upce.fei.nnpia.pshop.repository.UserRepository;
import cz.upce.fei.nnpia.pshop.security.dto.AuthenticationResponse;
import cz.upce.fei.nnpia.pshop.security.dto.LoginRequest;
import cz.upce.fei.nnpia.pshop.security.dto.RegisterRequest;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        final Optional<User> foundByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (foundByEmail.isPresent()) {
            return new ResponseEntity<>(new CustomExceptionHandler.EmailAlreadyExistsException("EMAIL_EXISTS"), HttpStatus.FORBIDDEN);
        }
        final Optional<User> foundByUsername = userRepository.findByUsername(registerRequest.getUsername());
        if (foundByUsername.isPresent()) {
            return new ResponseEntity<>(new CustomExceptionHandler.UsernameAlreadyExistsException("USERNAME_EXISTS"), HttpStatus.FORBIDDEN);
        }
        final User user = User.builder()
                              .firstname(registerRequest.getFirstname())
                              .lastname(registerRequest.getLastname())
                              .email(registerRequest.getEmail())
                              .username(registerRequest.getUsername())
                              .password(passwordEncoder.encode(registerRequest.getPassword()))
                              .creation_date(LocalDateTime.now())
                              .update_date(LocalDateTime.now())
                              .roles(Set.of(roleRepository.findByName("USER").orElseThrow()))
                              .build();
        userRepository.save(user);
        final String token = jwtService.generateToken(registerRequest.getUsername());
        return ResponseEntity.ok(AuthenticationResponse.builder()
                                                       .firstname(registerRequest.getFirstname())
                                                       .lastname(registerRequest.getLastname())
                                                       .token(token)
                                                       .build());
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        final Optional<User> found = userRepository.findByUsername(loginRequest.getUsername());
        if (found.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            } catch (AuthenticationException e) {
                return new ResponseEntity<>(new CustomExceptionHandler.WrongPasswordException("WRONG_PASSWORD"), HttpStatus.FORBIDDEN);
            }
            final String token = jwtService.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(AuthenticationResponse.builder()
                                                           .firstname(found.orElseThrow().getFirstname())
                                                           .lastname(found.orElseThrow().getLastname())
                                                           .token(token)
                                                           .build());
        } else {
            return new ResponseEntity<>(new CustomExceptionHandler.UsernameNotFoundException("USERNAME_NOT_FOUND"), HttpStatus.FORBIDDEN);
        }
    }
}
