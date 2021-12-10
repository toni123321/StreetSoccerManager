package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.News;
import soccer.game.streetsoccermanager.repository_interfaces.INewsRepository;
import soccer.game.streetsoccermanager.service.NewsService;
import soccer.game.streetsoccermanager.service_interfaces.INewsService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class NewsServiceUnitTest {
    @Mock
    INewsRepository newsRepository;
    INewsService newsService;

    @BeforeEach
    public void setUp()  {
        List<News> newsList = List.of(
                new News(1l,"Manchester United wins", "Man. United wins with a great goal from Ronaldo"),
                new News(2l,"Champions league today", "Barcelona vs Juventus")
        );
        when(newsRepository.getAll()).thenReturn(newsList);
        when(newsRepository.get(1l)).thenReturn(newsList.get(0));
        when(newsRepository.get(2l)).thenReturn(newsList.get(1));
        when(newsRepository.add(new News("Lionel Messi in PSG", "Stable performance from Leo"))).
                thenReturn(new News(3l,"Lionel Messi in PSG", "Stable performance from Leo"));
        when(newsRepository.update(new News(2l,"Champions league tomorrow", "Barcelona vs Juventus"))).
                thenReturn(new News(2l,"Champions league tomorrow", "Barcelona vs Juventus"));
        newsService = new NewsService(newsRepository);
    }

    @Test
    void GetAllNewsSuccessScenario() {
        // Act
        List<News> newsList = newsService.getAll();

        List<News> newsListExpected = new ArrayList<>();
        newsListExpected.add(new News(1l,"Manchester United wins", "Man. United wins with a great goal from Ronaldo"));
        newsListExpected.add(new News(2l,"Champions league today", "Barcelona vs Juventus"));

        // Assert
        Assertions.assertEquals(newsListExpected, newsList);
    }

    @Test
    void GetNewsSuccessScenario() {
        // Act
        News news = newsService.get(1l);

        // Assert
        Assertions.assertEquals(new News(1l,"Manchester United wins", "Man. United wins with a great goal from Ronaldo"), news);
    }

    @Test
    void DeleteNewsSuccessScenario(){
        // Act
        newsService.delete(1l);
        verify(newsRepository).delete(1l);
        when(newsRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, newsService.delete(2l));
        when(newsRepository.get(3l)).thenReturn(null);
        when(newsRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, newsService.delete(3l));
    }

    @Test
    void AddNewsSuccessScenario() {
        // Act
        News newNews = newsService.add(new News("Lionel Messi in PSG", "Stable performance from Leo"));
        // Assert
        Assertions.assertEquals(new News(3l,"Lionel Messi in PSG", "Stable performance from Leo"), newNews);
        Assertions.assertEquals(null, newsService.add(new News(3l,"Lionel Messi in PSG", "Stable performance from Leo")));
    }

    @Test
    void UpdateNewsSuccessScenario(){
        // Act
        News updatedNews = newsService.update(new News(2l,"Champions league tomorrow", "Barcelona vs Juventus"));
        // Assert
        Assertions.assertEquals(new News(2l,"Champions league tomorrow", "Barcelona vs Juventus"), updatedNews);
        Assertions.assertEquals(null, newsService.update(new News("Champions league tomorrow", "Barcelona vs Juventus")));
    }
}
