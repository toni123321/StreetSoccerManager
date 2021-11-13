//package soccer.game.streetSoccerManager.repository.UserEntity;
//
//import lombok.Getter;
//import org.springframework.stereotype.Repository;
//import soccer.game.streetSoccerManager.repository_interfaces.IUserRepository;
//import soccer.game.streetSoccerManager.model.entities.UserEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserStubDatabase implements IUserRepository {
//    @Getter
//    private List<UserEntity> users = new ArrayList<>();
//
//    public UserStubDatabase() {
////        users.add(new EndUser(0l, "peter@gmail.com", "123", "pete", 100));
////        users.add(new EndUser(1l, "john@gmail.com", "456", "jo", 10));
////
////        users.add(new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));
//        users.add(new UserEntity(0l, "peter@gmail.com", "123", null, null, null));
//    }
//
//    @Override
//    public List<UserEntity> getAll() {
//        return users;
//    }
//
//    @Override
//    public UserEntity get(Long id) {
//        for (UserEntity user : users) {
//            if (user.getId().equals(id))
//                return user;
//        }
//        return null;
//    }
//
//    @Override
//    public Boolean delete(Long id) {
//        UserEntity user = get(id);
//        if (user == null){
//            return false;
//        }
//
//        return users.remove(user);
//    }
//
//    @Override
//    public Boolean add(UserEntity user) {
//        if(user.getId() == null) {
//            user.setId(Long.valueOf(users.size()));
//        }
//        else if (this.get(user.getId()) != null){
//            return false;
//        }
//        users.add(user);
//        return true;
//    }
//
//    @Override
//    public Boolean update(UserEntity user) {
//
//        UserEntity oldUser = this.get(user.getId());
//        if (oldUser == null) {
//            return false;
//        }
//        oldUser.setEmail(user.getEmail());
//        oldUser.setPassword(user.getPassword());
//
////        if(user instanceof EndUser){
////            ((EndUser)oldUser).setNickname(((EndUser) user).getNickname());
////            ((EndUser)oldUser).setPoints(((EndUser) user).getPoints());
////
////        }
////        else{
////            ((Admin)oldUser).setFirstName(((Admin) user).getFirstName());
////            ((Admin)oldUser).setLastName(((Admin) user).getLastName());
////        }
//        return true;
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
