package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.domain.Role;
import com.example.warehousemanagementlufthansa.domain.User;
import com.example.warehousemanagementlufthansa.repository.UserRepository;
import com.example.warehousemanagementlufthansa.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service  @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving a new user {} to db", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving a new role {} to db" , role.getRoleName());

        return userRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}" , roleName , username);
        User user = userRepository.findByUsername(username);
        Role role = userRoleRepository.findByRoleName(roleName);
        user.getRole().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}"  , username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users" );

        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
       if(user == null){
           log.error("User not found");
           throw new UsernameNotFoundException("user not found");
       }else{
           log.info("User found {}", username);
       }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       user.getRole().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
       });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
