package hoangdz.discord.discordnotification;

import hoangdz.discord.discordnotification.Dto.Message;
import hoangdz.discord.discordnotification.Listener.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class DiscordNotification extends JavaPlugin {
    private net.dv8tion.jda.api.JDA jda;
    @Override
    public void onEnable() {
        // Plugin startup logic
        JDABuilder builder = JDABuilder.createDefault("DISCORD TOKEN");
        builder.addEventListeners(new MessageListener());

        jda = builder.build();
    }

    public void sendMessage(Message message) {
        TextChannel channel = jda.getTextChannelById(message.ChannelId);
        if (channel != null) {
            channel.sendMessage(message.Message).queue();
        } else {
            System.out.println("Channel not found with ID: " + message.ChannelId);
        }
    }

}
