package hoangdz.discord.dokiep.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack wand;
    public static ItemStack dokiepItem;
    public static void init() {
        createWand();
        createDokiepItem();
    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "Cổng thời không");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "Sử dụng để biến về Độ Kiếp Đài");
        lore.add(ChatColor.BLUE + "Item này được trao cho người có khả năng huỷ thiên diệt địa, vượt qua trời đất");
        lore.add(ChatColor.BLUE + "Hàng dùng 1 lần");
        lore.add(ChatColor.BLACK + "Dùng là mất hết kho đồ");
        meta.setLore(lore);
        item.setItemMeta(meta);

        wand = item;
    }

    private static void createDokiepItem() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "Arrow");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "Sử dụng để triệu hồi lôi kiếp tại Độ Kiếp Đài");
        lore.add(ChatColor.BLUE + "Độ dài lôi kiếp tuỳ theo cấp độ");
        lore.add(ChatColor.BLUE + "Trải qua lôi kiếp sẽ được tăng cấp");
        lore.add(ChatColor.BLACK + "Kẻ nhát gan chạy khỏi Độ Kiếp Đài coi như bỏ cuộc.");
        meta.setLore(lore);
        item.setItemMeta(meta);

        dokiepItem = item;
    }
}

