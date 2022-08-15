package com.example.football.core.position.web;

import com.example.football.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PositionBaseReq extends BaseRequest {
    @NotNull
    private String position;

    @NotEmpty
    private List<@Valid Id> players;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Id> getPlayers() {
        return players;
    }

    public void setPlayers(List<Id> players) {
        this.players = players;
    }
}
