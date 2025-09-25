package gateway.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    public static final String SECRET = "from_Heldon";
    public void validateToken(final String token) {
        Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
    }

    public SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
