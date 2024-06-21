package hoangdz.login.hoangdzlogin.Repository;

import hoangdz.login.hoangdzlogin.Entity.Player;

import java.util.List;

public interface IUserRepository {
    List<Player> GetAll();

    Player GetPlayer(String UUID);
    boolean Store(Player player);

}
