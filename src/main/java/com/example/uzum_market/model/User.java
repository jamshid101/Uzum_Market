package com.example.uzum_market.model;

import com.example.uzum_market.enums.Gender;
import com.example.uzum_market.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails , Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false,unique = true)
    private String email;

    private String name;
    @Column(nullable = false)
    private String password;

    private boolean accountNonLocked = true;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Long birthday;

    private String phoneNumber;

    private Long createDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Balance balance;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    private String code;


    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
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

    public boolean allOk() {
        return isAccountNonExpired() && isAccountNonLocked() && isCredentialsNonExpired() && isEnabled();
    }

}
