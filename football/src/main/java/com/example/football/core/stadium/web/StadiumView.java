package com.example.football.core.stadium.web;

import com.example.football.core.match.web.MatchView;
import com.example.football.core.tournament.web.TournamentView;

import java.io.Serializable;
import java.util.Set;

public class StadiumView implements Serializable {

    private long id;
    private String name;
    private int capacity;
    private Set<TournamentView> tournaments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<TournamentView> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<TournamentView> tournaments) {
        this.tournaments = tournaments;
    }


}
