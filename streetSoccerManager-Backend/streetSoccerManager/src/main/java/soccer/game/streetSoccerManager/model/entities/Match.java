package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;



@Entity
@Table(name ="match")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToOne
    @JoinColumn(name="matchInfoId")
    protected MatchInfo matchInfo;

    @OneToOne
    @JoinColumn(name="matchStatisticId")
    protected MatchStatistic matchStatistic;

    public Match(Long id, MatchInfo matchInfo,
                 MatchStatistic matchStatistic) {
        this.id = id;
        this.matchInfo = matchInfo;
        this.matchStatistic = matchStatistic;
    }
}
