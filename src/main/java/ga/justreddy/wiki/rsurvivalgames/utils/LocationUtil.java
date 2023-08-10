package ga.justreddy.wiki.rsurvivalgames.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JustReddy
 */
public class LocationUtil {

    private static final Set<Material> BAD_BLOCKS = new HashSet<>();

    static {
        BAD_BLOCKS.add(Material.LAVA);
        BAD_BLOCKS.add(Material.CACTUS);
        BAD_BLOCKS.add(Material.FIRE);
        BAD_BLOCKS.add(Material.WATER);
    }

    public static String toLocation(Location location) {
        return location.getWorld().getName() + " : " + location.getX() + " : " + location.getY() + " : " + location.getZ() + " : " + location.getYaw() + " : " + location.getPitch();
    }

    public static Location getLocation(String path) {
        String[] split = path.split(" : ");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }

    public static Location generateLocation(Cuboid cuboid) {
        Location highPoints = cuboid.getHighPoints();
        Location lowPoints = cuboid.getLowPoints();
        double minX = Math.min(highPoints.getX(), lowPoints.getX());
        double minY = Math.min(highPoints.getY(), lowPoints.getY());
        double minZ = Math.min(highPoints.getZ(), lowPoints.getZ());

        double maxX = Math.max(highPoints.getX(), lowPoints.getX());
        double maxY = Math.max(highPoints.getY(), lowPoints.getY());
        double maxZ = Math.max(highPoints.getZ(), lowPoints.getZ());
        Location location = new Location(highPoints.getWorld(),
                NumberUtil.randomDouble(minX, maxX),
                NumberUtil.randomDouble(minY, maxY),
                NumberUtil.randomDouble(minZ, maxZ)
                );
        if (!isLocationSafe(location)) {
            return generateLocation(cuboid);
        }
        return location;
    }

    public static boolean isLocationSafe(Location location){
        if (location == null) {
            throw new IllegalStateException("Location cannot be null!");
        }
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        World world = location.getWorld();
        if (world == null) {
            throw new IllegalStateException("World cannot be null!");
        }
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(BAD_BLOCKS.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

}
