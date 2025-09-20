package Auth_Microservice_PBD.Authentication.service;

import Auth_Microservice_PBD.Authentication.request.AuthenticationTokenRequest;
import Auth_Microservice_PBD.Authentication.request.LoginRequest;
import Auth_Microservice_PBD.Authentication.dto.UserDTO;
import Auth_Microservice_PBD.Authentication.entity.User;
import Auth_Microservice_PBD.Authentication.infra.security.TokenService;
import Auth_Microservice_PBD.Authentication.mapper.UserMapper;
import Auth_Microservice_PBD.Authentication.repository.UserRepository;
import Auth_Microservice_PBD.Authentication.request.RegisterRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public UserDTO register(RegisterRequest registerRequest){
        User userDataByUsername = (User)userRepository.findByUsername(registerRequest.username());
        if(userDataByUsername != null){
            throw new RuntimeException("user already has a registered username");
        }
        User userDataTransfer =  userMapper.registerRequestToUser(registerRequest);
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDataTransfer.getPassword());
        userDataTransfer.setPassword(encryptedPassword);
        return userMapper.userToUserDTO(userRepository.save(userDataTransfer));
    }

    public String login(LoginRequest authenticationDTO){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(
                    authenticationDTO.username(), authenticationDTO.password());
            var authenticationManager = authenticationConfiguration.getAuthenticationManager();
            var auth = authenticationManager.authenticate(usernamePassword);
            return tokenService.generateToken((User) auth.getPrincipal());
        }catch (Exception e){
            throw new RuntimeException("Error authenticating user");
        }

    }

    public boolean validateToken(String token){
        String username = tokenService.validationToken(token);
        return username != null && !username.isBlank();
    }

}
