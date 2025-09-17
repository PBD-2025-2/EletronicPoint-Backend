package Auth_Microservice_PBD.Authentication.mapper;

import Auth_Microservice_PBD.Authentication.dto.LoginDTO;
import Auth_Microservice_PBD.Authentication.dto.RegisterDTO;
import org.apache.catalina.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userToLoginDTO(LoginDTO loginDTO);

    User userToRegisterDTO(RegisterDTO registerDTO);

}
