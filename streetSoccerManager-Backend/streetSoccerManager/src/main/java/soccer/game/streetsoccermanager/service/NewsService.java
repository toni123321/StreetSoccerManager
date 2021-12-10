package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.News;
import soccer.game.streetsoccermanager.repository_interfaces.INewsRepository;
import soccer.game.streetsoccermanager.service_interfaces.INewsService;

import java.util.List;

@Service
public class NewsService implements INewsService {
    private INewsRepository dataStore;

    @Autowired
    public NewsService(INewsRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<News> getAll() {
        return dataStore.getAll();
    }

    @Override
    public News get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public News add(News news) {
        if(news.getId() == null) {
            return dataStore.add(news);
        }
        return null;
    }

    @Override
    public News update(News news) {
        if(news.getId() != null) {
            return dataStore.update(news);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }
}
