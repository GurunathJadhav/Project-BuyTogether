package com.buytogether.utilities.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${secret-key}")
    private String SECRET_KEY;

    @Value("${issuer}")
    private String issuer;

    @Value("${expiration-time}")
    private int expirationTime;

    private Algorithm algorithm;

    @PostConstruct
    private void postConstruct(){
        algorithm=Algorithm.HMAC256(SECRET_KEY);
    }

    public String generateToken(String username,String role){
       return JWT.create()
                .withClaim("username",username)
                .withClaim("role",role)
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .sign(algorithm);

    }



}
