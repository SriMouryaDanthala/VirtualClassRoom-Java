package com.example.VirtualClassRoom.service;

import com.example.VirtualClassRoom.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserLoginService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var completeUser = userDAO.getUserByUserName(username);
        if(!completeUser.isSuccess()) throw new UsernameNotFoundException("User Name "+username+" is not found");

        return new org.springframework.security.core.userdetails.User(
                completeUser.getServiceData().getUserName(),
                completeUser.getServiceData().getCredentials().getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(completeUser.getServiceData().getUserRole().getRoleName()))
        );
    }
}
