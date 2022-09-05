package com.example.football.core.match.annotations;

import com.example.football.core.match.Match;
import com.example.football.core.stadium.StadiumService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchValidator implements ConstraintValidator<MatchValidation, Match> {


    private StadiumService service;
    public MatchValidator(StadiumService stadiumService){this.service = stadiumService;}
    @Override
    public void initialize(MatchValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(Match match, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = true;
        if(service.getStadium(match.getStadium().getId()).getTournaments() != match.getTournament()){result = false;}
        return result;
    }
}
