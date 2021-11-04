package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.AdminConverter;
import soccer.game.streetSoccerManager.model.dtos.AdminDTO;
import soccer.game.streetSoccerManager.model.dtos.EndUserDTO;
import soccer.game.streetSoccerManager.model.entities.Admin;
import soccer.game.streetSoccerManager.model.entities.EndUser;
import soccer.game.streetSoccerManager.model.entities.User;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IUserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/admins")
public class AdminController {
    @Qualifier("userService")
    private IUserService userService;

    ModelMapper modelMapper = new ModelMapper();

    private AdminConverter adminConverter = new AdminConverter();

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getAdmin(@PathVariable(value = "id") Long id) {

        Admin user;
        if(userService.get(id) instanceof Admin){
            user = (Admin) userService.get(id);
        }
        else{
            user = null;
        }


        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllAdmins() {
        List<User> users = null;
        users = userService.getAll().stream().filter(Admin.class::isInstance).collect(Collectors.toList());
        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        //Admin admin = adminConverter.convertAdminDtoToAdmin(adminDTO);
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        if (!userService.add(admin)){
            String entity =  "user with id " + admin.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            //AdminDTO adminDTOtoReturn = adminConverter.convertAdminToAdminDto();
            AdminDTO adminDTOtoReturn = modelMapper.map(userService.get(admin.getId()), AdminDTO.class);
            return new ResponseEntity(adminDTOtoReturn,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        Admin admin = adminConverter.convertAdminDtoToAdmin(adminDTO);
        if (Boolean.TRUE.equals(userService.update(admin))) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid user id",HttpStatus.NOT_FOUND);
        }
    }


}
