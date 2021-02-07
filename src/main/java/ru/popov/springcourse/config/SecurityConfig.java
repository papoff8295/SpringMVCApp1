package ru.popov.springcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.popov.springcourse.security.jwt.JwtConfigurer;
import ru.popov.springcourse.security.jwt.JwtTokenProvider;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_ENDPOINT = "/admin/people/**";
    private static final String LOGIN_ENDPOINT = "/people/login";
    private static final String PEOPLE_ENDPOINT = "/people/**";


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http
            .httpBasic().disable()
            .csrf().disable()
            //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .authorizeRequests()
            .antMatchers(LOGIN_ENDPOINT).permitAll()
            .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
            .antMatchers(PEOPLE_ENDPOINT).hasRole("USER")
            .antMatchers("/login*").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.jsp")
            .loginProcessingUrl("/people/login").failureUrl("/login?error")
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
