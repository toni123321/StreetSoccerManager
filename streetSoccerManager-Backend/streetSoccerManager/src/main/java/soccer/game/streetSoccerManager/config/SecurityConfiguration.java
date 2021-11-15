package soccer.game.streetSoccerManager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import soccer.game.streetSoccerManager.filter.JWTAuthenticationFilter;
import soccer.game.streetSoccerManager.filter.JWTAuthorizationFilter;
import soccer.game.streetSoccerManager.handlers.AuthFailureHandler;
import soccer.game.streetSoccerManager.service.AuthenticationUserDetailService;

import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000/"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));


        http.cors().configurationSource(request -> corsConfiguration).and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                .antMatchers("/h2-ui/**").permitAll()
                .antMatchers("/teams").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/positions").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/positions").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/players").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/playerStats/**").hasAnyAuthority("ADMIN")
                .antMatchers("/playersPositionInfo").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/officialTeams").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/customTeams").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/friendlyMatches").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/formations").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthFailureHandler())
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
