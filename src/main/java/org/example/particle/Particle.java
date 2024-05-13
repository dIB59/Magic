package org.example.particle;

import org.example.Collidable;
import org.example.SpatialElement;

public final class Particle implements Collidable, SpatialElement {

    String color;
    int mass;
    int radius;
    double velX;
    double velY;
    int x;
    int y;


    public int getXCoordinate() {
        return this.x;
    }

    public int getYCoordinate() {
        return this.y;
    }

    public void resolveCollision(Particle particle) {
        int dx = particle.getXCoordinate() - this.getXCoordinate();
        int dy = particle.getYCoordinate() - this.getYCoordinate();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (!(distance < this.radius + particle.radius)) {
            return;
        }
        double angle = Math.atan2(dy, dx);

        double totalMass = this.mass + particle.mass;

        this.velX = ((this.mass - particle.mass) / totalMass) * this.velX + (2 * particle.mass / totalMass) * particle.velX;
        this.velY = ((this.mass - particle.mass) / totalMass) * this.velY + (2 * particle.mass / totalMass) * particle.velY;
        particle.velX = (2 * this.mass / totalMass) * this.velX - ((this.mass - particle.mass) / totalMass) * particle.velX;
        particle.velY = (2 * this.mass / totalMass) * this.velY - ((this.mass - particle.mass) / totalMass) * particle.velY;

        double overlap = (this.radius + particle.radius) - distance;
        double moveX = overlap * Math.cos(angle);
        double moveY = overlap * Math.sin(angle);
        this.x -= (int) (moveX / 2);
        this.y -= (int) (moveY / 2);
        particle.x += (int) (moveX / 2);
        particle.y += (int) (moveY / 2);
    }

    public int getBoundary(SpatialElement spatialElement) {
        return this.radius;
    }

    public Particle(String color, int mass, int radius, int velX, int velY, int x, int y) {
        this.color = color;
        this.mass = mass;
        this.radius = radius;
        this.velX = velX;
        this.velY = velY;
        this.x = x;
        this.y = y;
    }
}
