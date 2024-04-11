package cz.upce.fei.nnpia.pshop.security;

import cz.upce.fei.nnpia.pshop.security.dto.AuthenticationResponse;
import cz.upce.fei.nnpia.pshop.security.dto.LoginRequest;
import cz.upce.fei.nnpia.pshop.security.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody
            RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> AuthenticateAndGetToken(
            @RequestBody
            LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
