package soccer.game.streetSoccerManager.Interfaces;

public interface IRepository {
    Object getAll();
    Object get(Object obj);
    Object delete(Object obj);
    Object add(Object obj);
    Object update(Object obj);
}
