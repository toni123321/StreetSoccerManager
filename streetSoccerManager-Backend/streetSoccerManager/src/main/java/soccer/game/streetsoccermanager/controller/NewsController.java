package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.dtos.NewsDTO;
import soccer.game.streetsoccermanager.model.entities.News;
import soccer.game.streetsoccermanager.service_interfaces.INewsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/newsfeed")
public class NewsController {
    private INewsService newsService;
    private ModelMapper modelMapper;

    @Autowired
    public NewsController(INewsService newsService) {
        this.newsService = newsService;
        this.modelMapper = new ModelMapper();
    }

    @MessageMapping("/addNews")
    @SendTo("/topic/newsfeed")
    public NewsDTO AddNews(News news) throws Exception {
        Thread.sleep(1000);
        News inputtedNewsEntity = modelMapper.map(news, News.class);
        News createdNewsEntity = newsService.add(inputtedNewsEntity);
        NewsDTO createdNewsDTO = modelMapper.map(createdNewsEntity, NewsDTO.class);
        if (createdNewsDTO != null){
            return createdNewsDTO;
        } else {
            return new NewsDTO();
        }

    }

    @MessageMapping("/updateNews")
    @SendTo("/topic/newsfeed")
    public NewsDTO updateNews(@RequestBody NewsDTO news) throws Exception {
        Thread.sleep(1000);
        News inputtedNewsEntity = modelMapper.map(news, News.class);
        News updatedNewsEntity = newsService.add(inputtedNewsEntity);
        NewsDTO updatedNewsDTO = modelMapper.map(updatedNewsEntity, NewsDTO.class);
        if (updatedNewsDTO != null){
            return updatedNewsDTO;
        } else {
            return new NewsDTO();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<NewsDTO> getOneNews(@PathVariable(value = "id") Long id) {
        News newsEntity = newsService.get(id);
        NewsDTO newsDTO = modelMapper.map(newsEntity, NewsDTO.class);
        if(newsDTO != null) {
            return ResponseEntity.ok().body(newsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAll() {
        List<News> newsEntities = newsService.getAll();
        List<NewsDTO> newsDTOs = modelMapper.map(newsEntities, new TypeToken<List<NewsDTO>>() {}.getType());
        if(newsDTOs != null) {
            return ResponseEntity.ok().body(newsDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        if(Boolean.TRUE.equals(newsService.delete(id))) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }


}
