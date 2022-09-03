package com.example.football.core.stadium.annotations;

import com.example.football.core.stadium.Stadium;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StadiumValidator implements ConstraintValidator<StadiumValidation, Stadium> {


    @Override
    public  void initialize (StadiumValidation stadiumValidation){}

    @Override
    public boolean isValid(Stadium stadium, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = true;
        if(stadium.getCapacity() == 1000){result = false;}
        return result;
    }

}
