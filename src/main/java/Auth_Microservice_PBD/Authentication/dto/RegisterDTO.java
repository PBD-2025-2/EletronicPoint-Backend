package Auth_Microservice_PBD.Authentication.dto;

import Auth_Microservice_PBD.Authentication.enums.UserRole;

public record RegisterDTO(String name, String email, String username, String password, UserRole role) {}