package com.buytogether.servies.login;

import com.buytogether.payloads.generic.APIResponse;
import com.buytogether.payloads.login.LoginRequest;
import com.buytogether.utilities.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public APIResponse<String> verifyLogin(LoginRequest loginRequest){
        APIResponse<String> response = new APIResponse<>();

        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                response.setMessage("Login Successfully");
                response.setStatusCode(200);
                response.setData(jwtService.generateToken(loginRequest.getEmail(), authentication.getAuthorities().iterator().next().getAuthority()));
                return response;
            }

        } catch (Exception e) {
            response.setMessage("Login Failed: " + e.getMessage());
            response.setStatusCode(403);
            response.setData("Invalid credentials");
            return response;
        }

        response.setMessage("Login Failed");
        response.setStatusCode(403);
        response.setData("Invalid credentials");
        return response;
    }
}
