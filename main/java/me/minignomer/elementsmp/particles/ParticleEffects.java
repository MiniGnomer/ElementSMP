package me.minignomer.elementsmp.particles;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleEffects {
    public void spawnCircle(double radius, Particle particle, Location loc, Particle.DustOptions dustOptions) {
        for (double radians = 0; radians < Math.PI * 2; radians += Math.PI / 30) {
            double x = Math.cos(radians) * radius;
            double z = Math.sin(radians) * radius;
            Location newLoc = loc.clone().add(x, 0, z);
            newLoc.getWorld().spawnParticle(particle, newLoc, 1, 0, 0, 0, 0, dustOptions, true);

        }
    }

    public void spawnSphere(Location loc, double radiusSize, Particle particle, Particle.DustOptions dustOptions) {
        for (double i = 0; i <= Math.PI; i += Math.PI / 20) {
            double radius = Math.sin(i) * radiusSize;
            double y = Math.cos(i) * radiusSize;
            for (double radians = 0; radians < Math.PI * 2; radians += Math.PI / 10) {
                double x = Math.cos(radians) * radius;
                double z = Math.sin(radians) * radius;
                Location newLoc = loc.clone().add(x, y, z);

                newLoc.getWorld().spawnParticle(particle, newLoc, 1, 0, 0, 0, 0, dustOptions, true);
            }
        }
    }

    public void spawnCloud(double radius, Particle particle, Location loc, Particle.DustOptions dustOptions) {
        loc.getWorld().spawnParticle(particle, loc, 50, radius, radius / 2, radius, 0, dustOptions, true);
    }
}
