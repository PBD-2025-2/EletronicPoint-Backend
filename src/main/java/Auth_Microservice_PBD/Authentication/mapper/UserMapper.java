package Auth_Microservice_PBD.Authentication.mapper;

import Auth_Microservice_PBD.Authentication.dto.LoginDTO;
import Auth_Microservice_PBD.Authentication.dto.UserDTO;
import org.apache.catalina.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginDTO userToLoginDTO(User user);

    UserDTO userToUserDTO(User user);

}
