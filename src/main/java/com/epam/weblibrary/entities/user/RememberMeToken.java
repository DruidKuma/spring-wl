package com.epam.weblibrary.entities.user;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity for Spring Security Remember Me Token
 * @author Iurii Miedviediev
 * @version 1.0 Build 02.10.2014
 */
@Getter
@Setter
@Entity(name = "persistent_logins")
public class RememberMeToken {

    @Id
    @Column(length = 64)
    private String series;

    @Column(length = 64, nullable = false)
    private String username;

    @Column(length = 64, nullable = false)
    private String token;

    @Column(name = "last_used", nullable = false)
    private Date date;
}
