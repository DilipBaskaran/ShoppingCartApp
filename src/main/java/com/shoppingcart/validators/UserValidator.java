package com.shoppingcart.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shoppingcart.models.User;
import com.shoppingcart.services.UserService;
@Component
public class UserValidator implements Validator{
	
	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneno", "NotEmpty");
        if (user.getPhoneno().length() < 10 || user.getPhoneno().length() > 10) {
            errors.rejectValue("phoneno", "Size.user.phoneno");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "NotEmpty");
        if (user.getUser_name().length() < 6 || user.getUser_name().length() > 32) {
            errors.rejectValue("user_name", "Size.user.username");
        }
        if (userService.findByUserName(user.getUser_name()) != null) {
            errors.rejectValue("user_name", "Duplicate.user.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.user.password");
        }
    }

}
