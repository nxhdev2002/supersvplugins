package hoangdz.login.hoangdzlogin.Events;

import hoangdz.discord.discordnotification.Dto.Message;
import hoangdz.login.hoangdzlogin.Repository.IUserRepository;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import hoangdz.discord.discordnotification.DiscordNotification;
import org.bukkit.event.player.PlayerPortalEvent;

import static org.bukkit.Bukkit.getServer;

public class EventListener implements Listener {
    private final IUserRepository _userRepository;

    public EventListener(IUserRepository userRepository) {
        _userRepository = userRepository;
    }

    @EventHandler
    public void onPlayerLogin(final PlayerLoginEvent loginEvent) {
        DiscordNotification discordNotification = (DiscordNotification) getServer().getPluginManager().getPlugin("DiscordNotification");
        assert discordNotification != null;
        Message message = new Message();
        message.Guid = "614400012407013384";
        message.ChannelId = "1251458459204063292";
        message.Message = "Người chơi " + loginEvent.getPlayer().getDisplayName() + " vừa tham gia với IP: " + loginEvent.getHostname();
        discordNotification.sendMessage(message);

        // diịch chuyển người chơi đến toạ độ từ trước
        Player player = loginEvent.getPlayer();
        Location location = new Location(player.getWorld(), 2, 1, -1);

        player.teleport(location);
    }

    @EventHandler
    public void onPlayerLogin(final PlayerJoinEvent joinEvent) {
        // diịch chuyển người chơi đến toạ độ từ trước
        Player player = joinEvent.getPlayer();
        Location location = new Location(player.getWorld(), 2, 1, -1);

        player.teleport(location);
    }

    @EventHandler
    public void onBlockDestroy(final BlockDamageEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();

        if (location.getBlockX() < 10 && location.getBlockY() < 10 && location.getBlockZ() < 10) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();

        if (location.getBlockX() < 10 && location.getBlockY() < 10 && location.getBlockZ() < 10) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTele(final PlayerPortalEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        if (location.getBlockX() < 10 && location.getBlockY() < 10 && location.getBlockZ() < 10) {
            event.setCancelled(true);
            player.teleport(new Location(player.getWorld(), 54.567, 103, 126.403));
        }
    }
}
