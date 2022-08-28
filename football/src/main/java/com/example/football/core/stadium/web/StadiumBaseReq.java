package com.example.football.core.stadium.web;

import com.example.football.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class StadiumBaseReq extends BaseRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private int capacity;
    private List<@Valid Id> tournaments;

    private List<@Valid Id> matchId;

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

    public List<Id> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Id> tournaments) {
        this.tournaments = tournaments;
    }

    public List<Id> getMatch() {
        return matchId;
    }

    public void setMatch(List<Id> matchId) {
        this.matchId = matchId;
    }
}
