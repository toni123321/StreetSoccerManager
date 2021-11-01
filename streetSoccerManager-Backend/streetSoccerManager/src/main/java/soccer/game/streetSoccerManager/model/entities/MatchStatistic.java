package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "matchStatistic")
@NoArgsConstructor
@Data
public class MatchStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // result
    private int homeTeamGoals;
    private int awayTeamGoals;

    // Goals
    private String statistic;

    @OneToOne(mappedBy = "matchStatistic")
    @JsonIgnore
    protected Match match;

    public MatchStatistic(Long id, int homeTeamGoals, int awayTeamGoals, String statistic) {
        this.id = id;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.statistic = statistic;
    }
}
