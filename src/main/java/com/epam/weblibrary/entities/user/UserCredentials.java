package com.epam.weblibrary.entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Entity for user credentials
 * Spring Security makes use of this entity
 * @author Iurii Miedviediev
 * @version 1.0 Build 27.09.2014
 */
@Entity(name = "user_credentials")
@Data
@NoArgsConstructor
public class UserCredentials implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "credentials_id")
    private Long id;

    @Length(min = 3, max = 15)
    @Pattern(regexp = "^[a-z0-9_-]+$")
    private String login;

    @Length(min = 5, max = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public UserCredentials(String login, String password, Roles role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new LinkedList();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
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
