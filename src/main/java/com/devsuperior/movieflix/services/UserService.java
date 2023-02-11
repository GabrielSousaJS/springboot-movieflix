package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public UserDTO findCurrentUserProfile() {
        User user = authService.authenticated();
        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByEmail(username);

        if (user == null) {
            logger.error("Email not found " + username);
            throw new UsernameNotFoundException("Email not found");
        } else {
            logger.info("Email found " + username);
            return user;
        }
    }

}
