package com.example.football.core.stadium.annotations;

import com.example.football.core.match.Match;
import com.example.football.core.stadium.Stadium;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StadiumValidator implements ConstraintValidator<StadiumValidation, Stadium> {

    private Match match;

    public StadiumValidator(Match match){
        this.match = match;
    }

    @Override
    public  void initialize (StadiumValidation stadiumValidation){}

    @Override
    public boolean isValid(Stadium stadium, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = true;
        if(stadium.getTournaments() != match.getTournament()){result = false;}
        return result;
    }

}
