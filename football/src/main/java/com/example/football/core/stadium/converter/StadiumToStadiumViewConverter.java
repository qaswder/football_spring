package com.example.football.core.stadium.converter;

import com.example.football.core.match.Match;
import com.example.football.core.match.converter.MatchToMatchViewConverter;
import com.example.football.core.match.web.MatchView;
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
    private final MatchToMatchViewConverter matchToMatchViewConverter;

    public StadiumToStadiumViewConverter(TournamentToTournamentViewConverter tournamentToTournamentViewConverter,
                                         MatchToMatchViewConverter matchToMatchViewConverter){

        this.tournamentToTournamentViewConverter = tournamentToTournamentViewConverter;
        this.matchToMatchViewConverter = matchToMatchViewConverter;
    }
    @Override
    public StadiumView convert(@NonNull Stadium stadium) {
        StadiumView view = new StadiumView();
        view.setId(stadium.getId());
        view.setName(stadium.getName());
        view.setCapacity(stadium.getCapacity());

        Set<MatchView> matchViews = new HashSet<>();
        Set<Match> matches= stadium.getMatch();
        matches.forEach(match -> matchViews.add(matchToMatchViewConverter.convert(match)));
        view.setMatch(matchViews);

        Set<TournamentView> views = new HashSet<>();
        Set<Tournament> tournaments= stadium.getTournaments();
        tournaments.forEach(tournament -> views.add(tournamentToTournamentViewConverter.convert(tournament)));
        view.setTournaments(views);
        return view;
    }

}
