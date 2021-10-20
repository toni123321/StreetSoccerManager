package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Position {
    private Long id;
    private String category;
    private String position;

    public Position(Long id, String category, String position) {
        this.id = id;
        this.category = category;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position1 = (Position) o;
        return Objects.equals(getId(), position1.getId()) && Objects.equals(getCategory(), position1.getCategory()) && Objects.equals(getPosition(), position1.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategory(), getPosition());
    }
}
