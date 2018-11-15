package com.ub.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ub.model.AppUser;
import com.ub.service.UserService;

@Component
public class UserValidator implements Validator {
   
	@Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return AppUser.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        AppUser user = (AppUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        
        if (user.getFirstName().length() < 6 || user.getFirstName().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

//        if (!user.getPassword().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
    }
}