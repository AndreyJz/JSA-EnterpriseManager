package com.enterprisemanager.backend.infrastructure.utils.validations;

import com.enterprisemanager.backend.application.services.IPersonService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements
        ConstraintValidator<ExistsByUsername, String> {
    @Autowired
    private IPersonService service;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (service == null) {
            return true;
        }
        return !service.existsByUsername(username);
    }
}
