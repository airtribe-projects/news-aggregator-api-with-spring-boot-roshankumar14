package com.newsAggregator.newsAggregator.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;

public class JwtUtill  {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        public static String generateToken(String userName){

            System.out.println(secretKey.getEncoded());

            return Jwts.builder().setSubject(userName)
                    .setExpiration(new Date(System.currentTimeMillis() +8 * 60 * 60 * 1000))
                    .setIssuedAt(new Date())
                    .signWith(secretKey).compact();
        }

        public static boolean validateJwtToken(String authenticationHeader) {

            Claims claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(authenticationHeader)
                    .getBody();
            return claims.getExpiration().after(new Date());
        }
}
