package com.example.football.core.stadium.converter;

import com.example.football.core.stadium.Stadium;
import com.example.football.core.stadium.web.StadiumView;
import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.converter.TournamentToTournamentViewConverter;
import com.example.football.core.tournament.web.TournamentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class StadiumToStadiumViewConverter implements Converter<Stadium, StadiumView> {

    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;

    public StadiumToStadiumViewConverter(TournamentToTournamentViewConverter tournamentToTournamentViewConverter){

        this.tournamentToTournamentViewConverter = tournamentToTournamentViewConverter;
    }
    @Override
    public StadiumView convert(@NonNull Stadium stadium) {
        StadiumView view = new StadiumView();
        view.setId(stadium.getId());
        view.setName(stadium.getName());
        view.setCapacity(stadium.getCapacity());
        Set<TournamentView> views = new HashSet<>();
        Set<Tournament> tournaments= stadium.getTournaments();
        tournaments.forEach(tournament -> views.add(tournamentToTournamentViewConverter.convert(tournament)));
        view.setTournaments(views);
        return view;
    }

}
