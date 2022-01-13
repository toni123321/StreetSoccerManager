package soccer.game.streetsoccermanager.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="news")
@NoArgsConstructor
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    public News(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
