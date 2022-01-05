package com.mahlodi.atm.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahlodi.atm.DTO.userDTO;
import com.mahlodi.atm.model.ROLE;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@Data
public class User   implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String picture;
    private int studentId;
    private Date dob;
    @JsonIgnore
    private String password;
    @Column(unique=true)
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "user_role")
    private Set<ROLE> roles;

    public User(Long id) {
        this.setId(id);
    }

    public User(userDTO user) {
        this.dob = user.getDob();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public User() {
    }
}
