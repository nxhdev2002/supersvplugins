package hoangdz.discord.discordnotification;

import hoangdz.discord.discordnotification.Dto.Message;
import hoangdz.discord.discordnotification.Listener.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Base64;

public final class DiscordNotification extends JavaPlugin {
    private net.dv8tion.jda.api.JDA jda;
    private final String TK = "T0RRMU9EUXpPVE16TkRrM016a3pNVGMxLkdHVktJOS5kUjgtY0xCTFZFTWpycFp2cGlNcXlVTU11UjZZUDQ0VXkyTGxkSQ==";
    @Override
    public void onEnable() {
        // Plugin startup logic
        StartJDA();
    }

    public void sendMessage(Message message) {
        TextChannel channel = jda.getTextChannelById(message.ChannelId);
        if (channel != null) {
            channel.sendMessage(message.Message).queue();
        } else {
            System.out.println("Channel not found with ID: " + message.ChannelId);
        }
    }

    private void StartJDA() {
        Thread jdaThread = new Thread(() -> {
            JDABuilder builder = JDABuilder.createDefault(new String(Base64.getDecoder().decode(TK)));
            builder.addEventListeners(new MessageListener());
            jda = builder.build();
        });
        jdaThread.start();
    }
}
