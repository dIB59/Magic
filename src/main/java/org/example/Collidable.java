package org.example;

import org.example.particle.Particle;

@FunctionalInterface
public interface Collidable {

    public void resolveCollision(Particle particle);
}
