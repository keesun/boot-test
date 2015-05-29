package me.whiteship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Keeun Baik
 */
@Data
@EqualsAndHashCode(of = {"username", "password"})
@Entity
public class Account {

    @Id @GeneratedValue
    private int id;

    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
