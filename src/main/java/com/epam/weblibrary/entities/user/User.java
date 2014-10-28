package com.epam.weblibrary.entities.user;

import com.epam.weblibrary.entities.order.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.util.List;

/**
 * Entity for users
 * @author Iurii Miedviediev
 * @version 1.0 Build 11.09.2014
 */
@Entity(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    UserCredentials credentials;

    @Length(min = 1, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @Length(min = 1, max = 20)
    @Column(name = "last_name")
    private String lastName;

    @Email(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Order> orders;

    private float discount;

    public User(UserCredentials credentials, String first_name, String last_name, String email) {
        this.credentials = credentials;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
    }
}