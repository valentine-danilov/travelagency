package com.epam.travelagency.web.security.service;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.web.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService service;

    @Autowired
    public UserDetailsServiceImpl(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = service.findOneByLogin(s).orElseThrow(IllegalArgumentException::new);
        return UserDetailsImpl.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password("{noop}" + user.getPassword())
                .role(user.getRole())
                .reviews(user.getReviewList())
                .build();
    }


}
