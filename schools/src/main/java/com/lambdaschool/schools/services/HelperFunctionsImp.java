package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImp implements HelperFunctions
{
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause)
    {
        while ((cause != null) && !(cause instanceof ConstraintViolationException))
        {
            cause = cause.getCause();
        }

        List<ValidationError> validationErrorList = new ArrayList<>();

        if (cause != null)
        {
            ConstraintViolationException exception = (ConstraintViolationException) cause;
            for (ConstraintViolation constraintViolation : exception.getConstraintViolations())
            {
                ValidationError validationError = new ValidationError();

                validationError.setCode(constraintViolation.getInvalidValue().toString());
                validationError.setMessage(constraintViolation.getMessage());
                validationErrorList.add(validationError);
            }
        }

        return validationErrorList;
    }
}
