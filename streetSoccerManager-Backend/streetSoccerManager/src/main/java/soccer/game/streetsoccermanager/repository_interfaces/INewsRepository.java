package soccer.game.streetsoccermanager.repository_interfaces;

import soccer.game.streetsoccermanager.model.entities.News;

import java.util.List;

public interface INewsRepository {
    List<News> getAll();
    News get(Long id);
    Boolean delete(Long id);
    News add(News news);
    News update(News news);
    void deleteAll();
}
