package cz.upce.fei.nnpia.pshop.security;

import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.repository.RoleRepository;
import cz.upce.fei.nnpia.pshop.repository.UserRepository;
import cz.upce.fei.nnpia.pshop.security.dto.AuthenticationResponse;
import cz.upce.fei.nnpia.pshop.security.dto.LoginRequest;
import cz.upce.fei.nnpia.pshop.security.dto.RegisterRequest;
import cz.upce.fei.nnpia.pshop.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        final User user = User.builder()
                              .firstname(registerRequest.getFirstname())
                              .lastname(registerRequest.getLastname())
                              .username(registerRequest.getUsername())
                              .password(passwordEncoder.encode(registerRequest.getPassword()))
                              .creation_date(LocalDateTime.now())
                              .update_date(LocalDateTime.now())
                              .roles(Set.of(roleRepository.findByName("USER").orElseThrow()))
                              .build();
        userRepository.save(user);
        final String token = jwtService.generateToken(registerRequest.getUsername());
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        final User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        final String token = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder().token(token).build();
    }
}
