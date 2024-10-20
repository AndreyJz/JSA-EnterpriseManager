//package com.enterprisemanager.backend.application.services;
//
//import com.enterprisemanager.backend.domain.entities.Person;
//import com.enterprisemanager.backend.infrastructure.repositories.person.PersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class JpaUserDetailsService implements UserDetailsService {
//    @Autowired
//    private PersonRepository repository;
//
//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws
//            UsernameNotFoundException {
//        Optional<Person> userOptional = repository.findByUsername(username);
//        if (userOptional.isEmpty()) {
//            throw new UsernameNotFoundException(String.format("Username %s not found!", username));
//        }
//        Person user = userOptional.orElseThrow();
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        return new
//                User(user.getUsername(),
//                user.getPassword(),
//                true,
//                true, // la cuenta no ha expirado
//                true, // las credenciales no han expirado
//                true, // la cuenta no está bloqueada
//                authorities); // los roles del usuario
//    }
//}
