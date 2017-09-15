package net.blueapple.data.config;

import net.blueapple.domain.user.User;
import net.blueapple.domain.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Lithium on 9/15/2017.
 */
public class CustomUserDetailService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException("Username "+username+" not found");
        return user;
    }
}
