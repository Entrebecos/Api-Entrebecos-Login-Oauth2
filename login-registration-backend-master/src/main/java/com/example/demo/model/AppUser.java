package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;
import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;



// Tabela do banco de dados

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private Long telefone;
    private String endereco;
    private String cep;
    private LocalDate data_nascimento;
    private Boolean locked = false;
    private Boolean enabled = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type")
    private AuthenticationType authType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // Construtor com argumentos
    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String password,
                   Long telefone,
                   String endereco,
                   String cep,
                   LocalDate data_nascimento,
                   AuthenticationType authType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.data_nascimento = data_nascimento;
        this.authType = authType;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String email) {
        this.email = email;
    }
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long telefone() { return telefone; }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AuthenticationType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthenticationType authType) {
        this.authType = authType;
    }

}
