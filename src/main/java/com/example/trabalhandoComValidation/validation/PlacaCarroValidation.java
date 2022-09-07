package com.example.trabalhandoComValidation.validation;

import com.example.trabalhandoComValidation.validationcontrains.PlacaCarro;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PlacaCarroValidation implements ConstraintValidator<PlacaCarro, String> {
    @Override
    public void initialize(PlacaCarro constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override //isValid, vai me informar se o campo é valido ou não
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String placa = value == null ? "" : value; // Se o valor informado da placa for nulo, vai ser substituído por vazio
        return placa.matches("[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}");
    }
}
