package hoangdz.login.hoangdzlogin.Data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import hoangdz.login.hoangdzlogin.Entity.Player;

import java.sql.*;

public class PlayerDAO {
    private Dao<Player, String> playerDao = null;
    private final String connectionStr = "jdbc:postgresql://localhost:7890/minecraft1506?user=postgres&password=MC-022024";
    public PlayerDAO() {
        try {
            ConnectionSource connSource = new JdbcConnectionSource(connectionStr);
            TableUtils.createTableIfNotExists(connSource, Player.class);
            playerDao = DaoManager.createDao(connSource, Player.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void AddPlayer(Player player) throws SQLException {
        playerDao.create(player);
    }

    public Player GetPlayer(String UUID) throws SQLException {
        return playerDao.queryForId(UUID);
    }
}
