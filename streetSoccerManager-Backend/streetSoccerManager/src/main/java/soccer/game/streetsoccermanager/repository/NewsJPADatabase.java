package soccer.game.streetsoccermanager.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.News;
import soccer.game.streetsoccermanager.repository_interfaces.INewsRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.INewsJPARepository;

import java.util.List;

@Repository
public class NewsJPADatabase implements INewsRepository {

    @Autowired
    INewsJPARepository newsRepo;


    @Override
    public List<News> getAll() {
        return newsRepo.findAll();
    }

    @Override
    public News get(Long id) {
        return newsRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        newsRepo.deleteById(id);
        return true;
    }

    @Override
    public News add(News news) {
        return newsRepo.save(news);
    }

    @Override
    public News update(News news) {
        return newsRepo.save(news);
    }

    @Override
    public void deleteAll() {
        newsRepo.deleteAll();
    }
}
