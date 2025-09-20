package Auth_Microservice_PBD.Authentication.mapper;

import Auth_Microservice_PBD.Authentication.dto.UserDTO;
import Auth_Microservice_PBD.Authentication.entity.User;
import Auth_Microservice_PBD.Authentication.request.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User registerRequestToUser(RegisterRequest registerRequest);


}
