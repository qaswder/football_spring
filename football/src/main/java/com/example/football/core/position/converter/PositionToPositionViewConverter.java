package com.example.football.core.position.converter;

import com.example.football.core.player.Player;
import com.example.football.core.player.converter.PlayerToPlayerViewConverter;
import com.example.football.core.player.web.PlayerView;
import com.example.football.core.position.Position;
import com.example.football.core.position.web.PositionView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PositionToPositionViewConverter  implements Converter<Position, PositionView>{

    private final PlayerToPlayerViewConverter playerToPlayerViewConverter;
    public PositionToPositionViewConverter(PlayerToPlayerViewConverter playerToPlayerViewConverter){
        this.playerToPlayerViewConverter = playerToPlayerViewConverter;
    }

    @Override
    public PositionView convert(@NonNull Position position){
        PositionView view = new PositionView();
        view.setId(position.getId());
        view.setPosition(position.getPosition());
        Set<PlayerView> views = new HashSet<>();
        Set<Player> players = position.getPlayers();
        players.forEach(player -> views.add(playerToPlayerViewConverter.convert(player)));
        view.setPlayers(views);
        return view;
    }
}
