package soccer.game.streetsoccermanager.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name ="team")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
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
    @ToString.Exclude
    private Set<PlayerTeamInfo> playersTeamInfo;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    protected Set<Match> homeTeamsMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    protected Set<Match> awayTeamMatches;

    @Transient
    private int rating;

    public int getRating() {
        setRating(calcTeamRating());
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int calcTeamRating(){
        int overalRating = 0;
        int startingPlayersRating = 0;
        int reservesRating = 0;

        List<PlayerTeamInfo> startingPlayersTeamInfo = new ArrayList<>();
        List<PlayerTeamInfo> reservesTeamInfo = new ArrayList<>();

        if(this.getPlayersTeamInfo() != null) {
            startingPlayersTeamInfo = this.getPlayersTeamInfo().stream().filter(p -> p.getPlayer() != null && p.getPlayer().getPlayerPositionInfo() != null && p.getPlayer().getPlayerPositionInfo().isStarting()).collect(Collectors.toList());
            reservesTeamInfo = this.getPlayersTeamInfo().stream().filter(p -> p.getPlayer() != null && p.getPlayer().getPlayerPositionInfo() != null && !p.getPlayer().getPlayerPositionInfo().isStarting()).collect(Collectors.toList());
        }

        for (PlayerTeamInfo playerTeamInfo: startingPlayersTeamInfo) {
            if(playerTeamInfo.getPlayer() != null &&
                    playerTeamInfo.getPlayer().getPlayerAdditionalInfo() != null &&
                    playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats() != null
            ) {
                startingPlayersRating += playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats().getOverallRating();
            }
        }
        for (PlayerTeamInfo playerTeamInfo: reservesTeamInfo) {
            if(playerTeamInfo.getPlayer() != null &&
                    playerTeamInfo.getPlayer().getPlayerAdditionalInfo() != null &&
                    playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats() != null
            ) {
                reservesRating += playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats().getOverallRating();
            }
        }

        if(!playersTeamInfo.isEmpty() &&
                !startingPlayersTeamInfo.isEmpty()){
            startingPlayersRating /= startingPlayersTeamInfo.size();
        }

        if(!playersTeamInfo.isEmpty() &&
                !reservesTeamInfo.isEmpty()){
            reservesRating /= reservesTeamInfo.size();
        }

        overalRating = ((int) Math.round((startingPlayersRating * 0.8) + (reservesRating * 0.2)));

        return overalRating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getRating() == team.getRating() && getName().equals(team.getName()) && getFormation().equals(team.getFormation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFormation(), getRating());
    }
}
