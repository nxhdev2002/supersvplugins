package hoangdz.login.hoangdzlogin;

import hoangdz.login.hoangdzlogin.Events.EventListener;
import hoangdz.login.hoangdzlogin.Events.PlayerOnSafeZone;
import hoangdz.login.hoangdzlogin.Repository.IUserRepository;
import hoangdz.login.hoangdzlogin.Repository.UserRepository;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class HoangDZLogin extends JavaPlugin {
    @Override
    public void onEnable() {
        IUserRepository userRepository;
        userRepository = new UserRepository();
        getServer().getPluginManager().registerEvents(new EventListener(userRepository), this);
        getServer().getPluginManager().registerEvents(new PlayerOnSafeZone(), this);
    }
}
