package com.karlchu.book.core.service.impl;

import com.karlchu.book.core.repository.UserRepository;
import com.karlchu.book.model.Users;
import com.karlchu.book.utility.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private transient final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByEmail(username);
        if (user == null) {
            log.error("Could not load user info for login with username:" + username + ".");
            throw new UsernameNotFoundException("Could not load user info for login with username:" + username + ".");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Constants.LOGON_DEFAULT_PERMISSION));
        grantedAuthorities.add(new SimpleGrantedAuthority(Constants.LOGON_DEFAULT_PERMISSION));

        List<String> roles = user.getRoles();
        if(roles != null) {
            for (String role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}
