package com.buytogether.controllers.customer;

import com.buytogether.payloads.customer.CustomerRegistrationRequest;
import com.buytogether.payloads.generic.APIResponse;
import com.buytogether.servies.customer.CustomerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buytogether/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/registration")
    public ResponseEntity<APIResponse<String>> customerRegistration(@RequestBody CustomerRegistrationRequest data){
        APIResponse<String> apiResponse = customerService.registerCustomer(data);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatusCode()));
    }
}
