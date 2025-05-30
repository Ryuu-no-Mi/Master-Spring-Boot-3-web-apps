package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.User;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.repositories.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username: %s no existe en el sistema", username));
        }

        User user = userOptional.orElseThrow();

        //convirtiendo los roles de la clase ROLE a una lista GrantedAuthority
        List<GrantedAuthority> authority = user.getRoles().stream()
            .map(role-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                    authority);
    }

}
