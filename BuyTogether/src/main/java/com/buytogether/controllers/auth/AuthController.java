package com.buytogether.controllers.auth;

import com.buytogether.payloads.generic.APIResponse;
import com.buytogether.payloads.login.LoginRequest;
import com.buytogether.servies.login.LoginService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buytogether/auth")
public class AuthController {

    private LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginRequest loginRequest){
        APIResponse<String> response = loginService.verifyLogin(loginRequest);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
