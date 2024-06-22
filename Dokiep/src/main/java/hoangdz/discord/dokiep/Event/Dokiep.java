package hoangdz.discord.dokiep.Event;

import hoangdz.discord.dokiep.Localization.vi;
import hoangdz.discord.redispubsub.RedisManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class Dokiep implements Listener {

    public Dokiep() {}

    @EventHandler
    public void onPlayerUpLevel(final PlayerLevelChangeEvent event) {
        if (event.getNewLevel() < event.getOldLevel()) return;
        Player player = event.getPlayer();
        String msg = String.format(ChatColor.RED + vi.LEVEL_UP, player.getDisplayName());
        player.sendMessage(msg);
        player.setLevel(event.getOldLevel());
        RedisManager.getInstance().publish(String.format("[1].%s.%d-%d", player.getDisplayName(), event.getOldLevel(), event.getNewLevel()));
    }
}
