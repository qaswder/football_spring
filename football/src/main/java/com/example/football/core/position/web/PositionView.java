package com.example.football.core.position.web;

import com.example.football.core.player.web.PlayerView;

import java.util.HashSet;
import java.util.Set;

public class PositionView {
    private  long id;
    private String position;
    private Set<PlayerView> players = new HashSet<PlayerView>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<PlayerView> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerView> players) {
        this.players = players;
    }
}
