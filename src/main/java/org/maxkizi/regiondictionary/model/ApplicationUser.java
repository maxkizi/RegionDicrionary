package org.maxkizi.regiondictionary.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maxkizi.regiondictionary.model.base.BaseDeletedEntity;
import org.maxkizi.regiondictionary.model.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Table(name = "application_user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser extends BaseDeletedEntity implements UserDetails {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;
    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "application_user_role_binding",
            joinColumns = @JoinColumn(name = "application_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_code")
    )
    private Set<Role> userRoles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(
                role.getRoleName())).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return login;
    }
}
