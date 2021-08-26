package com.example.collectionsApp.service.user;

import com.example.collectionsApp.models.user.Role;
import com.example.collectionsApp.models.user.User;
import com.example.collectionsApp.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(long id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public User findByName(String name){
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(name));
        return userOptional.orElse(null);
    }

    public boolean add(User user) {
        try {
            User userFromDb = userRepository.findByUsername(user.getUsername());
            if (userFromDb != null) {
                return false;
            }
            if (user.getUsername().equals("admin"))
                user.setRoles(Collections.singleton(Role.ADMIN));
            else
                user.setRoles(Collections.singleton(Role.USER));

            user.setDataRegistration(new Date());
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUrl("https://st.depositphotos.com/1779253/5140/v/600/depositphotos_51402215-stock-illustration-male-avatar-profile-picture-use.jpg");
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            System.out.println("Error: (add user). " + e.getMessage());
            return false;
        }

    }

    public boolean changeUser(User user){
        try {
            if(userRepository.findByUsername(user.getUsername()) != null)
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            System.out.println("Error: (change user). " + e.getMessage());
            return false;
        }
    }

    public boolean deleteById(long id){
        User userFromDb = findById(id);
        if (userFromDb != null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean isAuthentication(){
        return getAuthenticationID() != 0;
    }

    public boolean isAdmin(){
        if(isAuthentication()) {
            return Role.ADMIN.toString().equals(getAuthenticationUser().getUserRole());
        }
        return false;
    }

    public long getAuthenticationID(){
        User user = getAuthenticationUser();
        if(user != null)
            return user.getId();
        else
            return 0;
    }

    public String getAuthenticationName(){
        User user = getAuthenticationUser();
        if(user != null)
            return user.getUsername();
        else
            return "";
    }

    public User getAuthenticationUser(){
        User user = null;
        try{
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){
            return null;
        }
        return user;
    }

    public int sizeAllUsers(){
        return userRepository.findAll().size();
    }

    public void setRoleUser(List<User> users){
        for(User user : users) {
            user.changeRole(Role.USER);
            changeUser(user);
        }
    }

    public void setRoleAdmin(List<User> users){
        for(User user : users) {
            user.changeRole(Role.ADMIN);
            changeUser(user);
        }
    }



}