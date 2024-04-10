package cz.upce.fei.nnpia.pshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired AuthenticationManager authenticationManager;

    @Autowired JwtService jwtService;

    @PostMapping("/auth")
    public AuthResponseDTO AuthenticateAndGetToken(
            @RequestBody
            AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),
                                                                                                                   authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return AuthResponseDTO.builder().accessToken(jwtService.generateToken(authRequestDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("Invalid request");
        }
    }
}
