package com.group.MCTestProject.services;

import com.group.MCTestProject.models.Sign;
import com.group.MCTestProject.repositories.SignRepository;
import com.group.MCTestProject.security.SignDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Service
public class SignDetailsService implements UserDetailsService {

    private final SignRepository signRepository;

    @Autowired
    public SignDetailsService(SignRepository peopleRepository) {
        this.signRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Sign> sign = signRepository.findByUsername(s);

        if (sign.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new SignDetails(sign.get());
    }
}
