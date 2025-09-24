package Auth_Microservice_PBD.Authentication.controller;

import Auth_Microservice_PBD.Authentication.request.AuthenticationTokenRequest;
import Auth_Microservice_PBD.Authentication.request.LoginRequest;
import Auth_Microservice_PBD.Authentication.request.RegisterRequest;
import Auth_Microservice_PBD.Authentication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest userData) {
        return ResponseEntity.ok("Token:  " + authService.login(userData));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest userData) {
        return new ResponseEntity<>(authService.register(userData), HttpStatus.CREATED);
    }

    @PostMapping("/authentication")
    public ResponseEntity authentication(@RequestBody AuthenticationTokenRequest authenticationTokenRequest){
        boolean isValid = authService.validateToken(authenticationTokenRequest.token());
        if(isValid){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
    }
}
