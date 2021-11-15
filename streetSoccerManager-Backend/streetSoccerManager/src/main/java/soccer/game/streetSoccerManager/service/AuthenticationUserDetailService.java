package soccer.game.streetSoccerManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.service_interfaces.IUserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailService implements UserDetailsService {
    private final IUserService userService;
    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = userService.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
