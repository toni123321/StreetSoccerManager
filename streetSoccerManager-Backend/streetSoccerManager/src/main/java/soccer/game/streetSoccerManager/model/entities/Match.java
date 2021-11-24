package soccer.game.streetSoccerManager.model.entities;

import lombok.*;
import javax.persistence.*;



@Entity
@Table(name ="match")
@Data
@EqualsAndHashCode(exclude = {"id", "homeTeam", "awayTeam"})
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    @JoinColumn(name="homeTeamId")
    protected Team homeTeam;
    @ManyToOne
    @JoinColumn(name="awayTeamId")
    protected Team awayTeam;

    protected String result;
    protected String statistic;

    public Match(Team homeTeam, Team awayTeam, String result, String statistic) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.result = result;
        this.statistic = statistic;
    }
}
