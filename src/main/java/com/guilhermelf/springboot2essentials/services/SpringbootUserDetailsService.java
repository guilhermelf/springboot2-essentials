package com.guilhermelf.springboot2essentials.services;

import com.guilhermelf.springboot2essentials.repository.SpringbootUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpringbootUserDetailsService implements UserDetailsService {
    private final SpringbootUserRepository springbootUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(springbootUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Springboot user not found"));
    }
}
