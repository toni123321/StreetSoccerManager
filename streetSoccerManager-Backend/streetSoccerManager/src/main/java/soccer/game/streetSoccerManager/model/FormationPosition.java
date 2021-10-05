package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormationPosition {
    private int id;
    private String name; // CM
    private Formation formation; // 1-2-1
    private double x_cor;
    private double y_cor;
    private Player player; // Player (id)


//    team => formation
//    const [formationPos, setPos] = useState();
//    getAllByFormation();
//
//    const [players, setPlayers] = useState();
//    getAllPlayersByTeam();
//
//

    // func(newboxId,prevBoxId)

//    box1 - currentPlayer
//    box2 - player2
//
//    prevPlayer = player1
//    box1.player = player2


    public FormationPosition(int id, String name, Formation formation, double x_cor, double y_cor, Player player) {
        this.id = id;
        this.name = name;
        this.formation = formation;
        this.x_cor = x_cor;
        this.y_cor = y_cor;
        this.player = player;
    }
}
