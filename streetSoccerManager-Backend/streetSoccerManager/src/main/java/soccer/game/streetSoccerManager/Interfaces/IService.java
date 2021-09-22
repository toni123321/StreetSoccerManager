package soccer.game.streetSoccerManager.Interfaces;

public interface IService {

    Object getAll();
    Object get(Object obj);
    Boolean delete(Object obj);
    Boolean add(Object obj);
    Boolean update(Object obj);
}
