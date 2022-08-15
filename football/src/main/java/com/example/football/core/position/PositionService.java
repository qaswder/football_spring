package com.example.football.core.position;

import com.example.football.base.BaseRequest;
import com.example.football.core.player.Player;
import com.example.football.core.player.PlayerRepo;
import com.example.football.core.position.converter.PositionToPositionViewConverter;
import com.example.football.core.position.web.PositionBaseReq;
import com.example.football.core.position.web.PositionView;
import com.example.football.error.EntityNotFoundException;
import com.example.football.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class PositionService {

    private final PositionRepo positionRepo;
    private final PositionToPositionViewConverter positionToPositionViewConverter;
    private final PlayerRepo playerRepo;
    private final MessageUtil messageUtil;

    public PositionService(PositionRepo positionRepo,
                           PositionToPositionViewConverter positionToPositionViewConverter,
                           PlayerRepo playerRepo,
                           MessageUtil messageUtil){
        this.positionRepo = positionRepo;
        this.positionToPositionViewConverter = positionToPositionViewConverter;
        this.playerRepo = playerRepo;
        this.messageUtil = messageUtil;
    }

    public Position findPositionOrThrow(long id){
        return positionRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("position.NotFound", id)));
    }

    public PositionView getPosition(Long id) {
        Position position = findPositionOrThrow(id);
        return positionToPositionViewConverter.convert(position);
    }

    public Page<PositionView> findAllPosition(Pageable pageable) {
        Page<Position> positions = positionRepo.findAll(pageable);
        List<PositionView> positionViews = new ArrayList<>();
        positions.forEach(position -> {
            PositionView positionView = positionToPositionViewConverter.convert(position);
            positionViews.add(positionView);
        });
        return new PageImpl<>(positionViews, pageable, positions.getTotalElements());
    }

    public PositionView create(PositionBaseReq req) {
        Position position = new Position();
        this.prepare(position, req);
        Position positionSave = positionRepo.save(position);
        return positionToPositionViewConverter.convert(positionSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            positionRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("position.NotFound", id));
        }
    }

    public PositionView update(Position position, PositionBaseReq req) {
        Position newPosition = this.prepare(position,req);
        Position positionSave = positionRepo.save(newPosition);
        return positionToPositionViewConverter.convert(positionSave);
    }

    private Position prepare(Position position, PositionBaseReq req) {
        position.setPosition(req.getPosition());
        List<Player> playerList = playerRepo.findAllById(req.getPlayers()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Player> players = new HashSet<>(playerList);
        position.setPlayers(players);
        return position;
    }
}
