package com.example.WebMeetingPlanner.Service;

import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RegistrationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=repo.findByEmail(email);
        if(user!= null)
        {
            throw  new UsernameNotFoundException("User found in the database");
        }
        return new CustomUserDetails(user);

    }
}
