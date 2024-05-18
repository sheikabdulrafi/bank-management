package com.rafisheik.bankmanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rafisheik.bankmanagement.Model.BankModel;
import com.rafisheik.bankmanagement.Repository.BankRepo;

public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BankRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankModel user = repo.findByEmailId(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder().username(user.getUserName()).password(user.getPassword()).roles(user.getRoles()).build();
    }
    
}
