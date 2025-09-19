package Auth_Microservice_PBD.Authentication.request;

import Auth_Microservice_PBD.Authentication.enums.UserRole;

public record RegisterRequest(String name, String email, String username, String password, UserRole role) {}