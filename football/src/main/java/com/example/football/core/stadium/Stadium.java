package com.example.football.core.stadium;

import com.example.football.core.match.Match;
import com.example.football.core.stadium.annotations.StadiumValidation;
import com.example.football.core.tournament.Tournament;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fc_stadium")
public class Stadium{
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "fc_stadium_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fc_stadium_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fc_stadium_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;
    @NotEmpty
    @StadiumValidation
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_match_stadium",
            joinColumns = { @JoinColumn(name = "id_stadium") },
            inverseJoinColumns = { @JoinColumn(name = "id_match") })
    private Set<Match> match = new HashSet<>();

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_tournament_stadium",
            joinColumns = { @JoinColumn(name = "id_stadium") },
            inverseJoinColumns = { @JoinColumn(name = "id_tournament") })
    private Set<Tournament> tournaments = new HashSet<>();

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

    public Set<Match> getMatch() {
        return match;
    }

    public void setMatch(Set<Match> match) {
        this.match = match;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
