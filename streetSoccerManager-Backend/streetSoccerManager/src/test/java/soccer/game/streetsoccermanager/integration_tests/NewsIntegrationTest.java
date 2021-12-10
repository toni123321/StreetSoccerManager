package soccer.game.streetsoccermanager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.News;
import soccer.game.streetsoccermanager.service.NewsService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class NewsIntegrationTest {
    //Arrange
    @Autowired
    NewsService newsService;
    List<News> newsList = new ArrayList<>();
    List<News> newsListExpected = new ArrayList<>();

    @BeforeEach
    void clearDB() {
        // Clear
        newsService.deleteAll();
        newsListExpected.clear();

        // Add
        newsService.add(new News("Manchester United wins", "Man. United wins with a great goal from Ronaldo"));
        newsService.add(new News("Champions league today", "Barcelona vs Juventus"));
        newsList = newsService.getAll();
        newsListExpected.add(new News(newsList.get(0).getId(),"Manchester United wins", "Man. United wins with a great goal from Ronaldo"));
        newsListExpected.add(new News(newsList.get(1).getId(),"Champions league today", "Barcelona vs Juventus"));
    }

    @Test
    void getAllNewsSuccessScenario() {
        // Assert
        Assertions.assertEquals(newsListExpected, newsService.getAll()) ;
    }


    @Test
    void GetNewsSuccessScenario() {
        // Act
        News news = newsService.get(newsList.get(0).getId());

        // Assert
        Assertions.assertEquals(new News(newsList.get(0).getId(),"Manchester United wins", "Man. United wins with a great goal from Ronaldo"), news);
    }

    @Test
    void DeleteNewsSuccessScenario(){
        // Act
        newsService.delete(newsList.get(0).getId());
        newsListExpected.remove(0);

        // Assert
        Assertions.assertEquals(newsListExpected, newsService.getAll());
    }

    @Test
    void AddNewsSuccessScenario() {
        // Act
        newsService.add(new News("Lionel Messi in PSG", "Stable performance from Leo"));
        newsList = newsService.getAll();
        Long lastIndexId = newsList.get(newsList.size() - 1).getId();
        newsListExpected.add(new News(lastIndexId, "Lionel Messi in PSG", "Stable performance from Leo"));

        // Assert
        Assertions.assertEquals(newsListExpected, newsService.getAll());
    }

    @Test
    void UpdateNewsSuccessScenario(){
        // Act
        newsService.update(new News(newsList.get(0).getId(),"Manchester United last minute win", "Man. United wins with a great goal from Ronaldo"));
        newsListExpected.set(0, new News(newsList.get(0).getId(),"Manchester United last minute win", "Man. United wins with a great goal from Ronaldo"));

        // Assert
        Assertions.assertEquals(newsListExpected, newsService.getAll());
    }
}
