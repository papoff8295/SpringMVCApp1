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
    private static final String ADMIN_ENDPOINT = "/admin/**";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String REGISTER_ENDPOINT = "/register";
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
           //.httpBasic().disable()
            .csrf().disable()
            //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //.and()
            .authorizeRequests()
            .antMatchers(REGISTER_ENDPOINT).not().fullyAuthenticated()
            //.antMatchers(LOGIN_ENDPOINT).anonymous()
            .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
            .antMatchers(PEOPLE_ENDPOINT).hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            //.loginProcessingUrl("/people/login")
            //.failureUrl("/login?error")
            .defaultSuccessUrl("/").permitAll()
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/")
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }

}
