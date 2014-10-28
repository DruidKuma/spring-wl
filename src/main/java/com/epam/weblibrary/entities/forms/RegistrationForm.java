package com.epam.weblibrary.entities.forms;

import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import lombok.Data;
import javax.validation.Valid;

/**
 * Form for registration of new users
 * @author Iurii Miedviediev
 * @version 1.0 Build 01.10.2014
 */
@Data
public class RegistrationForm {

    @Valid
    UserCredentials credentials;

    @Valid
    User user;

    String passRepeat;
}
