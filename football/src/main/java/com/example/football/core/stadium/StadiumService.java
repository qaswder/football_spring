package com.example.football.core.stadium;

import com.example.football.base.BaseRequest;
import com.example.football.core.match.Match;
import com.example.football.core.match.MatchRepo;
import com.example.football.core.stadium.converter.StadiumToStadiumViewConverter;
import com.example.football.core.stadium.web.StadiumBaseReq;
import com.example.football.core.stadium.web.StadiumView;
import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.TournamentRepo;
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
public class StadiumService {

    private StadiumRepo stadiumRepo;
    private StadiumToStadiumViewConverter stadiumToStadiumViewConverter;
    private TournamentRepo tournamentRepo;
    private MessageUtil messageUtil;

    public StadiumService(StadiumRepo stadiumRepo,
                          StadiumToStadiumViewConverter stadiumToStadiumViewConverter,
                          TournamentRepo tournamentRepo,
                          MessageUtil messageUtil){
        this.stadiumRepo = stadiumRepo;
        this.stadiumToStadiumViewConverter = stadiumToStadiumViewConverter;
        this.tournamentRepo = tournamentRepo;
        this.messageUtil = messageUtil;
    }

    public StadiumView getStadium(Long id) {
        Stadium stadium = findStadiumOrThrow(id);
        return stadiumToStadiumViewConverter.convert(stadium);
    }

    public Stadium findStadiumOrThrow(Long id) {
        return stadiumRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("stadium.NotFound", id)));
    }

    public Page<StadiumView> findAllStadium(Pageable pageable){
        Page<Stadium> stadiums = stadiumRepo.findAll(pageable);
        List<StadiumView> stadiumViews = new ArrayList<>();
        stadiums.forEach(stadium -> {
            StadiumView stadiumView = stadiumToStadiumViewConverter.convert(stadium);
            stadiumViews.add(stadiumView);
        });
        return new PageImpl<>(stadiumViews, pageable, stadiums.getTotalElements());
    }

    public StadiumView create(StadiumBaseReq req) {
        Stadium stadium = new Stadium();
        this.prepare(stadium,req);
        Stadium stadiumSave = stadiumRepo.save(stadium);
        return stadiumToStadiumViewConverter.convert(stadiumSave);
    }

    @Transactional
    public void delete(Long id){
        try {
            stadiumRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("stadium.NotFound", id));
        }
    }

    public StadiumView update(Stadium stadium, StadiumBaseReq req){
        Stadium newStadium = this.prepare(stadium,req);
        Stadium stadiumSave = stadiumRepo.save(newStadium);
        return stadiumToStadiumViewConverter.convert(stadiumSave);
    }

    public Stadium prepare(Stadium stadium, StadiumBaseReq req){
        stadium.setName(req.getName());
        stadium.setCapacity(req.getCapacity());
        List<Tournament> tournamentList = tournamentRepo.findAllById(req.getTournaments()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Tournament> tournaments = new HashSet<>(tournamentList);
        stadium.setTournaments(tournaments);
        return stadium;
    }

}
