package com.natali.cultickets.service.detailsService;

import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.model.Role;
//import com.natali.cultickets.service.impl.UserServiceImpl;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
//import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserServiceImpl userService;

    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = new UserDto();
//        UserDto user = this.userService.findUserDtoByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Cannot find user with provided username"));
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);
    }

    public static List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        return userRoles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }

    private UserDetails buildUserForAuthentication(UserDto user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getActive(), true, true, true, authorities);
    }


}
