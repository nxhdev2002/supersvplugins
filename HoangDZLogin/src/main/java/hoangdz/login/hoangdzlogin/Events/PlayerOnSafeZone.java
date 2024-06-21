package hoangdz.login.hoangdzlogin.Events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEnterBlockEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PlayerOnSafeZone implements Listener  {
    private final double[] position1 = new double[]{60, 103, 131};
    private final double[] position2 = new double[]{49, 103, 122};


    @EventHandler
    public void onBlockDamage(final BlockDamageEvent event) {
        Location location = event.getBlock().getLocation();
        if (isSafeZone(location)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntitySpawn(final EntitySpawnEvent event) throws InterruptedException {
        Entity entity = event.getEntity();
        if (isSafeZone(event.getEntity().getLocation()) && entity.getType() != EntityType.PLAYER) {
            entity.getWorld().strikeLightning(event.getEntity().getLocation());
            if (entity instanceof LivingEntity) {
                TimeUnit.SECONDS.sleep(1);
                ((LivingEntity) entity).setHealth(0);
            }
        }
    }

    @EventHandler
    public void onMonsterInSafeZone(final EntityEnterBlockEvent event) throws InterruptedException {
        Entity entity = event.getEntity();
        if (isSafeZone(event.getEntity().getLocation()) && entity instanceof Monster) {
            entity.getWorld().strikeLightning(event.getEntity().getLocation());
            TimeUnit.SECONDS.sleep(1);
            ((LivingEntity) entity).setHealth(0);
        }
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent event) throws InterruptedException {
        Entity entity = event.getEntity();
        if (isSafeZone(event.getEntity().getLocation())) {
            Entity damager = event.getDamager();
            if (damager instanceof LivingEntity) {
                TimeUnit.SECONDS.sleep(1);
                ((LivingEntity) damager).setHealth(0);
                event.setCancelled(true);
            }
        }
    }


    private boolean isSafeZone(Location location) {
        if (45 < location.getBlockX() && location.getBlockX() < 60) {
            return 100 < location.getBlockZ() && location.getBlockZ() < 150;
        }
        return false;
    }
}
