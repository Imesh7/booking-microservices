package com.booking.authenticationserver.utils;

import com.booking.authenticationserver.exceptions.auth.JwtTokenFailedException;
import com.booking.authenticationserver.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiredTimeInSeconds}")
    private int jwtTokenExpiredTimeInSeconds;

   public  String generateToken(User user){
       Claims claims = Jwts.claims().setSubject(user.getEmail());
       return Jwts.builder()
               .setClaims(claims)
               .claim("id", user.getId())
               .signWith(SignatureAlgorithm.HS512,secret)
               .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiredTimeInSeconds))
               .compact();
    }


    public Long getUserId(String token){
      Claims claims = validateToken(token);
      return Long.parseLong( (String)claims.get("id"));
    }

   public Claims validateToken(String token){
       return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public  String getNewAccessToken(String token){
        try {
           Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            User user = new User();

            user.setEmail(claims.getSubject());
            user.setId(Long.parseLong(claims.get("id").toString()));

            return generateToken(user);

        } catch (ExpiredJwtException e){
            throw new JwtTokenFailedException("Your Token is expired");
        } catch (UnsupportedJwtException e){
            throw new JwtTokenFailedException("Your Token is unsupported");
        }catch (MalformedJwtException e){
            throw new JwtTokenFailedException("Your Token is not correct");
        }catch (SignatureException e){
            throw new JwtTokenFailedException("Your Token is Failed");
        }catch (IllegalArgumentException e){
            throw new JwtTokenFailedException("Your Token is Invalid {}" + e.getMessage());
        }
    }

}
