package soccer.game.streetSoccerManager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;
import soccer.game.streetSoccerManager.service.TeamService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    private static final TeamService teamService = new TeamService();


    @GetMapping("{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(value = "id") Long id) {
        Team team = teamService.getTeam(id);

        if(team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = null;
        teams = teamService.getAllTeams();

        if(teams != null) {
            return ResponseEntity.ok().body(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


/*

    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/students/3
    public ResponseEntity deletePost(@PathVariable int id) {
        fakeDataStore.deleteStudent(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    //POST at http://localhost:XXXX/students/
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (!fakeDataStore.add(student)){
            String entity =  "Student with student number " + student.getStudentNumber() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "student" + "/" + student.getStudentNumber(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    //PUT at http://localhost:XXXX/students/


    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.update(student)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid student number.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/students/{id}
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id,  @RequestParam("name") String name, @RequestParam("country") String countryCode) {
        Student student = fakeDataStore.getStudent(id);
        if (student == null){
            return new ResponseEntity("Please provide a valid student number.",HttpStatus.NOT_FOUND);
        }

        Country country = fakeDataStore.getCountry(countryCode);
        if (country == null){
            return new ResponseEntity("Please provide a valid country code.",HttpStatus.BAD_REQUEST);
        }

        student.setName(name);
        student.setCountry(country);
        return ResponseEntity.noContent().build();
    }
*/



}
