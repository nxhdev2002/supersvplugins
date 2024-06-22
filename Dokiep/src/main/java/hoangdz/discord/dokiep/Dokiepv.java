package hoangdz.discord.dokiep;

import hoangdz.discord.discordnotification.DiscordNotification;
import hoangdz.discord.discordnotification.Dto.Message;
import hoangdz.discord.dokiep.Commons.Commons;
import hoangdz.discord.dokiep.Dto.PlayerUpLevelMsgDto;
import hoangdz.discord.dokiep.Event.Dokiep;
import hoangdz.discord.dokiep.Items.ItemManager;
import hoangdz.discord.redispubsub.RedisManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dokiepv extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        ItemManager.init();
        getServer().getPluginManager().registerEvents(new Dokiep(), this);
        DiscordNotification discordNotification = (DiscordNotification) getServer().getPluginManager().getPlugin("DiscordNotification");

        RedisManager.getInstance().subscribe(s -> {
            PlayerUpLevelMsgDto dto = Commons.ParseRedisMessage(s);
            Message msg = new Message();
            msg.Guid = "614400012407013384";
            msg.ChannelId = "1251458459204063292";
            msg.Message = String.format("%s vừa từ cấp %d lên cấp %d...", dto.Player, dto.OldLevel, dto.NewLevel);
            discordNotification.sendMessage(msg);
            return s;
        });
    }

    @Override
    public void onDisable() {
    }

}
