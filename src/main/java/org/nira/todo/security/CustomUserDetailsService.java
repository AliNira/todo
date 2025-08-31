package org.nira.todo.security;

import lombok.RequiredArgsConstructor;
import org.nira.todo.entity.User;
import org.nira.todo.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepo.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this username or email"));
        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority(user.getRole().name());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Set.of(grantedAuthorities));
    }
}
