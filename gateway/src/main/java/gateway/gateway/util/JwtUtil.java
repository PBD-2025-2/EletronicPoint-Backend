package gateway.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public static final String SECRET = "from_Heldon";

    public void validateToken(final String token) {
        try {
            Algorithm algorithm = getSignAlgorithm(SECRET);
            JWT.require(algorithm)
                    .withIssuer("EletronicPoint")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("unauthorized access");
        }
    }

    public Algorithm getSignAlgorithm(String SECRET){
        return Algorithm.HMAC256(SECRET);
    }
}
