package com.atman.aahara.Admin;


import com.atman.aahara.Enum.Role;
import com.atman.aahara.Global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "admin")
public class Admin extends BaseEntity {

    @Column(unique = true)
    private String email;

    private String password;

    private Role role;

}
