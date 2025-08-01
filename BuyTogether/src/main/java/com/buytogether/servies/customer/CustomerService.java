package com.buytogether.servies.customer;

import com.buytogether.entities.Customer;
import com.buytogether.payloads.customer.CustomerRegistrationRequest;
import com.buytogether.payloads.generic.APIResponse;
import com.buytogether.repositories.customer.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public APIResponse<String> registerCustomer(CustomerRegistrationRequest data){
        APIResponse<String> response =new APIResponse<>();
       Customer customer=new Customer();
       BeanUtils.copyProperties(data,customer);
       customer.setPassword(passwordEncoder.encode(data.getPassword()));

        Customer savedCustomer = customerRepository.save(customer);
        if(savedCustomer!=null){
            response.setMessage("Customer Registration Completed");
            response.setStatusCode(201);
            response.setData(savedCustomer.getName()+" Your Registration is completed");
            return response;

        }
        response.setMessage("Customer Registration Failed");
        response.setStatusCode(500);
        response.setData("Your Registration is failed");
        return response;
    }
}
