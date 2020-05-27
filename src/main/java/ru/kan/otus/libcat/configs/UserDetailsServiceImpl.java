package ru.kan.otus.libcat.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kan.otus.libcat.domain.Role;
import ru.kan.otus.libcat.domain.Users;
import ru.kan.otus.libcat.repositories.UsersRepository;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) {
        Users applicationUser = userRepository.findByUsername(name);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(name);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(),
                getAuthorityList(applicationUser.getRoles()));

    }

    private Set<GrantedAuthority> getAuthorityList(Set<Role> roles) {
        Set<GrantedAuthority> authoritySet = null;

        if (roles != null) {
            authoritySet = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        } else {
            authoritySet = Collections.<GrantedAuthority>emptySet();
        }

        return authoritySet;
    }
}
