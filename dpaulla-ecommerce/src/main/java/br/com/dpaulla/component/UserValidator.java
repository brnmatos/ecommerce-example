package br.com.dpaulla.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import br.com.dpaulla.model.User;
import br.com.dpaulla.service.UserService;

@Component
public class UserValidator implements Validator {
   
	@Autowired
    private UserService userService;
	
	Util cpfUtil = new Util();

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 10 || user.getUsername().length() > 100) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "NotEmpty");
        if (!Util.isValid(user.getCpf())) {
            errors.rejectValue("cpf", "Cpf.Validator");
		}
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usernameConfirm", "NotEmpty");
        if (!user.getUsernameConfirm().equals(user.getUsername())) {
            errors.rejectValue("usernameConfirm", "Diff.userForm.usernameConfirm");
        }


    }
    
}