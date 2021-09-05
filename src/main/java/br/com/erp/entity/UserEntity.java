package br.com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class UserEntity {

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = TABLE, generator = "userGenerator")
    @TableGenerator(name = "userGenerator", table = "hibernate_sequences")
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

}
