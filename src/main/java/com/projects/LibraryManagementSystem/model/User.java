package com.projects.LibraryManagementSystem.model;


import com.projects.LibraryManagementSystem.enums.UserStatus;
import com.projects.LibraryManagementSystem.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "\"USER\"")
public class User extends TimeStamps implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 15,unique = true)
    private String phoneNo;

    @Column(length=50,unique = true,nullable = false)
    private String email;

    private String address;

    @Enumerated
    private UserStatus userStatus;

    @Enumerated
    private UserType userType;

    private String password;

    private String authorities;

    @Transient
    private String temp;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn> txnList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] auth = authorities.split(",");
        return Arrays.stream(auth).map(a->new SimpleGrantedAuthority(a)).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

