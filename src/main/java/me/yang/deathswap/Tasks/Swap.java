package me.yang.deathswap.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;
import java.util.UUID;

public class Swap extends BukkitRunnable {
    @Override
    public void run() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();

        Player greenPlayer = Bukkit.getPlayer(Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().toString());
        Player bluePlayer = Bukkit.getPlayer(Objects.requireNonNull(sm.getMainScoreboard().getTeam("Green")).getEntries().toString());

        Location greenLocation = Objects.requireNonNull(greenPlayer).getLocation();
        Location blueLocation = Objects.requireNonNull(bluePlayer).getLocation();

        Objects.requireNonNull(Bukkit.getWorld("Test-World-In-1162")).spawnEntity(greenLocation, EntityType.ARMOR_STAND);
        Entity armorStand = Bukkit.getEntity(UUID.fromString("tp"));
        Location armorStandLocation = Objects.requireNonNull(armorStand).getLocation();

        bluePlayer.teleport(armorStandLocation);
        greenPlayer.teleport(blueLocation);

        int spawn = Objects.requireNonNull(sm.getMainScoreboard().getObjective("settings")).getScore("spawn").getScore();
        if (spawn == 1) {
            bluePlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 255));
            greenPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 255));
        } else if (spawn == 2) {
            bluePlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3, 255));
            greenPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3, 255));
        }

        armorStand.remove();
        new Timer().run();

        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Swap!");
        bluePlayer.playSound(blueLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        greenPlayer.playSound(greenLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }
}
