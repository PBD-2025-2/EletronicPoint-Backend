package Auth_Microservice_PBD.Authentication.controller;

import Auth_Microservice_PBD.Authentication.dto.AuthenticationDTO;
import Auth_Microservice_PBD.Authentication.entity.User;
import Auth_Microservice_PBD.Authentication.repository.UserRepository;
import Auth_Microservice_PBD.Authentication.request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    // Passar o service ao inves do repository
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO userData) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest userData) {
        if (this.userRepository.findByUsername(userData.username()) != null) {return ResponseEntity.badRequest().build();}

        String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());
        User newUser = new User(userData.name(), userData.email(), userData.username(), encryptedPassword, userData.role());

        // Salvar usuario pelo service.
        return null;
    }
}
