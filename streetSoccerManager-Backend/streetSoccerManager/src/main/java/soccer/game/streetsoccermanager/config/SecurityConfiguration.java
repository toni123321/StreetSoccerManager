package soccer.game.streetsoccermanager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import soccer.game.streetsoccermanager.filter.JWTAuthenticationFilter;
import soccer.game.streetsoccermanager.filter.JWTAuthorizationFilter;
import soccer.game.streetsoccermanager.service.AuthenticationUserDetailService;

import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;

    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000/"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));


        http.cors().configurationSource(request -> corsConfiguration).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                .antMatchers("/h2-ui/**").permitAll()
                .antMatchers("/login").permitAll()
//                .antMatchers("/**").permitAll()
                .antMatchers("/teams").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/positions").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/players").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/playerStats").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/playersPositionInfo").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/officialTeams").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/customTeams").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/friendlyMatches").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers("/formations").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
