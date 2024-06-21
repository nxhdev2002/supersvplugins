package hoangdz.login.hoangdzlogin.Repository;

import hoangdz.login.hoangdzlogin.Data.PlayerDAO;
import hoangdz.login.hoangdzlogin.Entity.Player;

import java.sql.SQLException;
import java.util.List;

public class UserRepository implements IUserRepository {
    private PlayerDAO playerDAO;
    public UserRepository() {
//        playerDAO = new PlayerDAO();
    }
    @Override
    public List<Player> GetAll() {
        System.out.println("TEST");
        return null;
    }

    @Override
    public Player GetPlayer(String UUID) {
        try {
            return playerDAO.GetPlayer(UUID);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.toString());
            return null;
        }
    }

    @Override
    public boolean Store(Player player) {
        return false;
    }
}
