package pl.edu.pja.gdansk.voyage2.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.NullRequestCache;
import pl.edu.pja.gdansk.voyage2.security.service.SecuredUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecuredUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        AuthenticationEntryPoint authenticationEntryPoint = new RestAuthenticationEntryPoint();

        http
                .authorizeRequests()
                .antMatchers("user/token").authenticated()
                .antMatchers("/user").permitAll()
                .antMatchers("/management/**").permitAll()
                .antMatchers("/docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .csrf()
                .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
