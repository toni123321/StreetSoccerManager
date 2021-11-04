package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;


@Entity
@Table(name ="matchInfo")
@NoArgsConstructor
@Data
public class MatchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="homeTeamId")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name="awayTeamId")
    private Team awayTeam;

    @OneToOne(mappedBy = "matchInfo")
    @JsonIgnore
    protected Match match;

    public MatchInfo(Long id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
