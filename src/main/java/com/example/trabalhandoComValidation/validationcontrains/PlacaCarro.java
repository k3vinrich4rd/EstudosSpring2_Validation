package com.example.trabalhandoComValidation.validationcontrains;

import com.example.trabalhandoComValidation.validation.PlacaCarroValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//Responsavel pela validação da placa do carro
@Documented
@Constraint(validatedBy = PlacaCarroValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlacaCarro { //Se coloca o @Interface para a ide entender que se trata de uma anotação

    String message() default "Placa de carro inválida"; // Mensagem que vai aparecer para o usuário
    Class<?> [] groups() default {}; // Padrão da validação
    Class<? extends Payload> [] payload() default {}; // Padrão da validação






}
