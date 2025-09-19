package Auth_Microservice_PBD.Authentication.service;

import Auth_Microservice_PBD.Authentication.dto.UserDTO;
import Auth_Microservice_PBD.Authentication.repository.UserRepository;
import Auth_Microservice_PBD.Authentication.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDTO register(RegisterRequest registerRequest){
        User userData = (User)userRepository.findByUsername(registerRequest.username());
        if(userData == null){

        }
        return null;
    }

}
