package com.group.MCTestProject.security;

import com.group.MCTestProject.models.Sign;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Neil Alishev
 */
public class SignDetails implements UserDetails {
    private final Sign sign;

    public SignDetails(Sign sign) {
        this.sign = sign;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.sign.getPassword();
    }

    @Override
    public String getUsername() {
        return this.sign.getUsername();
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

    // Нужно, чтобы получать данные аутентифицированного пользователя
    public Sign getSign() {
        return this.sign;
    }
}
