package com.example.football.core.player.converter;

import com.example.football.core.player.Player;
import com.example.football.core.player.web.PlayerView;
import com.example.football.core.team.Team;
import com.example.football.core.team.converter.TeamToTeamViewConverter;
import com.example.football.core.team.web.TeamView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerToPlayerViewConverter implements Converter<Player, PlayerView> {

    private final TeamToTeamViewConverter teamToTeamViewConverter;

    public PlayerToPlayerViewConverter(TeamToTeamViewConverter teamToTeamViewConverter) {
        this.teamToTeamViewConverter = teamToTeamViewConverter;
    }

    @Override
    public PlayerView convert(@NonNull Player player) {
        PlayerView view = new PlayerView();
        view.setId(player.getId());
        view.setName(player.getName());
        view.setSurname(player.getSurname());
        view.setAge(player.getAge());
        view.setHeight(player.getHeight());
        view.setWeight(player.getWeight());
        Set<TeamView> views = new HashSet<>();
        Set<Team> teams= player.getTeams();
        teams.forEach(team -> {
            TeamView teamView = teamToTeamViewConverter.convert(team);
            views.add(teamView);
        });
        view.setTeams(views);
        return view;
    }
}
