package soccer.game.streetsoccermanager.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name ="team")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@EqualsAndHashCode(exclude = {"playersTeamInfo", "homeTeamsMatches", "awayTeamMatches"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="formationId")
    private Formation formation;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlayerTeamInfo> playersTeamInfo;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Set<Match> homeTeamsMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Set<Match> awayTeamMatches;

    @Transient
    private int rating;

    @PostLoad
    public void onPostLoad() {
        this.rating = calcTeamRating();
    }

    private int calcTeamRating(){
        int rating = 0;
        int startingPlayersRating = 0;
        int reservesRating = 0;

        List<PlayerTeamInfo> startingPlayersTeamInfo = new ArrayList<>();
        List<PlayerTeamInfo> reservesTeamInfo = new ArrayList<>();

        if(this.getPlayersTeamInfo() != null) {
            startingPlayersTeamInfo = this.getPlayersTeamInfo().stream().filter(p -> p.getPlayer().getPlayerPositionInfo().isStarting()).collect(Collectors.toList());
            reservesTeamInfo = this.getPlayersTeamInfo().stream().filter(p -> !p.getPlayer().getPlayerPositionInfo().isStarting()).collect(Collectors.toList());
        }

        for (PlayerTeamInfo playerTeamInfo: startingPlayersTeamInfo) {
            startingPlayersRating += playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats().getOverallRating();
        }
        for (PlayerTeamInfo playerTeamInfo: reservesTeamInfo) {
            reservesRating += playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats().getOverallRating();
        }

        if(!playersTeamInfo.isEmpty() &&
                !startingPlayersTeamInfo.isEmpty()){
            startingPlayersRating /= startingPlayersTeamInfo.size();
        }

        if(!playersTeamInfo.isEmpty() &&
                !reservesTeamInfo.isEmpty()){
            reservesRating /= reservesTeamInfo.size();
        }

        rating = ((int) Math.round((startingPlayersRating * 0.8) + (reservesRating * 0.2)));

        return rating;
    }

    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }

    public Team(String name, Formation formation) {
        this.name = name;
        this.formation = formation;
    }

}
