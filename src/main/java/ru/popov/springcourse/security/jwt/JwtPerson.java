package ru.popov.springcourse.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtPerson implements UserDetails {

    private int id;
    private String name;
    private int age;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public JwtPerson(int id, String name, int age, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public JwtPerson() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
