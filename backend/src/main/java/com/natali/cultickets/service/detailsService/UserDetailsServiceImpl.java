package com.natali.cultickets.service.detailsService;

import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.Role;
//import com.natali.cultickets.service.impl.UserServiceImpl;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
//import javax.transaction.Transactional;
import com.natali.cultickets.repository.UserRepository;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Getter
    private AuthInfo user;
    private List<GrantedAuthority> authorities;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthInfo user = this.userRepository.findUserByLogin(login);

        List<GrantedAuthority> authorities = getUserAuthority(this.userRepository.getRoles(user.getUserId()));

        return buildUserForAuthentication();
    }

    public static List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        return userRoles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }

    private UserDetails buildUserForAuthentication() {
        return new org.springframework.security.core.userdetails.User(this.user.getLogin(), this.user.getPasswordHash(),
                true, true, true, true, this.authorities);
    }

    public List<GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }
}
