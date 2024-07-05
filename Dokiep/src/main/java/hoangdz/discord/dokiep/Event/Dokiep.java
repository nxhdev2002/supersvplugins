package hoangdz.discord.dokiep.Event;

import hoangdz.discord.dokiep.Dokiepv;
import hoangdz.discord.dokiep.Items.ItemManager;
import hoangdz.discord.dokiep.Localization.vi;
import hoangdz.discord.redispubsub.RedisManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.bukkit.Bukkit.getServer;

public class Dokiep implements Listener {

    public Dokiep() {}
    private final double[] X = {135, 234};
    private final double[] Y = {250, 300};
    private final double[] Z = {9, 100};
    private final PotionEffectType[] pot = {
            PotionEffectType.HERO_OF_THE_VILLAGE,
            PotionEffectType.BAD_OMEN,
            PotionEffectType.HEALTH_BOOST,
            PotionEffectType.INSTANT_DAMAGE,
            PotionEffectType.SPEED,
            PotionEffectType.NIGHT_VISION
    };

    @EventHandler
    public void onPlayerUpLevel(final PlayerLevelChangeEvent event) {
        if (event.getNewLevel() < event.getOldLevel()) return;
        Player player = event.getPlayer();
        if (Objects.equals(player.getCustomName(), "Độ kiếp sư")) {
            player.setCustomName(null);
            return;
        }
        String msg = String.format(ChatColor.RED + vi.LEVEL_UP, player.getDisplayName());
        player.sendMessage(msg);
        player.setLevel(event.getOldLevel());
        RedisManager.getInstance().publish(String.format("[1].%s.%d-%d", player.getDisplayName(), event.getOldLevel(), event.getNewLevel()));
        player.getInventory().addItem(ItemManager.wand);
    }


    @EventHandler
    public void onUseTeleportItem(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().getItemMeta().equals(ItemManager.wand.getItemMeta())) {
                Location location = new Location(event.getPlayer().getWorld(), 114.8, 232.5, 59.4);
                event.getPlayer().teleport(location);
                event.getItem().setAmount(0);
                event.getPlayer().getInventory().addItem(ItemManager.dokiepItem);
            }
        }
    }

    @EventHandler
    public void onUseDoKiepItem(PlayerItemConsumeEvent event) {
        AtomicBoolean isSuccess = new AtomicBoolean(true);
        AtomicBoolean isDone = new AtomicBoolean(false);
        Player player = event.getPlayer();
            if (event.getItem().getItemMeta().getDisplayName().equals(ItemManager.dokiepItem.getItemMeta().getDisplayName())) {
                player.getInventory().remove(event.getItem());
                Location location = player.getLocation();
                if (event.getPlayer().hasPotionEffect(PotionEffectType.HEALTH_BOOST)) return;
                if (inDoKiepLocation(location)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 600, 1));
                    player.setCustomName("Độ kiếp sư");


                    /// Create bossbar
                    BossBar bossBar = getServer().createBossBar(
                            "Lôi Kiếp",
                            BarColor.RED,
                            BarStyle.SEGMENTED_10,
                            BarFlag.CREATE_FOG,
                            BarFlag.PLAY_BOSS_MUSIC
                    );

                    bossBar.setVisible(true);
                    bossBar.addPlayer(event.getPlayer());
                    AtomicReference<Double> progress = new AtomicReference<>((double) 1);

                    /// start
                    int taskId = Bukkit.getScheduler().runTaskTimer(Dokiepv.getPlugin(Dokiepv.class), () -> {
                        if (inDoKiepLocation(player.getLocation())) {
                            if (!isDone.get()) {
                                player.getWorld().strikeLightning(player.getLocation());
                                bossBar.setProgress(progress.get());
                                progress.set(progress.get() - 0.5);
                            }
                        } else {
                            isDone.set(true);
                            isSuccess.set(false);
                            bossBar.setVisible(false);
                            player.setCustomName(null);
                            player.sendMessage("Bạn đã ra khỏi khu vực Độ Kiếp Đài, chúc may mắn lần sau :D.");
                        }
                    }, 0L, 100L).getTaskId();

                    Bukkit.getScheduler().runTaskLater(Dokiepv.getPlugin(Dokiepv.class), () -> {
                        Bukkit.getScheduler().cancelTask(taskId);
                        if (isSuccess.get()) {
                            bossBar.setVisible(false);
                            player.sendMessage("Chúc mừng bạn đã độ kiếp thành công :)");
                            player.sendMessage("Bạn được ban cho sức mạnh tối thượng trong 5p.");
                            player.setLevel(player.getLevel() + 1);
                            Random rand = new Random();
                            int effRand = rand.nextInt(pot.length + 1) + 1;
                            player.addPotionEffect(new PotionEffect(pot[effRand], 6000, 1));
                        }
                    }, 200L);
                } else {
                    event.setCancelled(true);
                    player.sendMessage("Về Độ Kiếp Đài để tiếp tục");
                }
            }
    }

    private boolean inDoKiepLocation(Location location) {
        return location.getBlockX() > X[0] && location.getBlockX() < X[1]
                && location.getBlockY() > Y[0] && location.getBlockY() < Y[1]
                && location.getBlockZ() > Z[0] && location.getBlockZ() < Z[1];
    }
}
