package org.example.particle;

import org.example.Collidable;
import org.example.SelfCollidable;
import org.example.SpatialElement;
import org.example.VelocityDampener;

public final class Particle implements SelfCollidable<Particle>, SpatialElement, Collidable, VelocityDampener {

    String color;
    int mass;
    int radius;
    double velX;
    double velY;
    int x;
    int y;
    double velocityDampener = 0.9;


    public Particle(String color, int mass, int radius, int velX, int velY, int x, int y) {
        this.color = color;
        this.mass = mass;
        this.radius = radius;
        this.velX = velX;
        this.velY = velY;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getXCoordinate() {
        return this.x;
    }

    @Override
    public int getYCoordinate() {
        return this.y;
    }

    @Override
    public void resolveCollision(Particle particle) {
        int dx = particle.getXCoordinate() - this.getXCoordinate();
        int dy = particle.getYCoordinate() - this.getYCoordinate();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (!(distance < this.radius + particle.radius)) {
            return;
        }
        double angle = Math.atan2(dy, dx);

        double totalMass = this.mass + particle.mass;

        this.velX = ((this.mass - particle.mass) / totalMass) * this.velX + (2 * particle.mass / totalMass)
                * particle.velX * this.velocityDampener;
        this.velY = ((this.mass - particle.mass) / totalMass) * this.velY + (2 * particle.mass / totalMass)
                * particle.velY * this.velocityDampener;
        particle.velX = (2 * this.mass / totalMass) * this.velX - ((this.mass - particle.mass) / totalMass)
                * particle.velX * this.velocityDampener;
        particle.velY = (2 * this.mass / totalMass) * this.velY - ((this.mass - particle.mass) / totalMass)
                * particle.velY * this.velocityDampener;

        double overlap = (this.radius + particle.radius) - distance;
        double moveX = overlap * Math.cos(angle);
        double moveY = overlap * Math.sin(angle);
        this.x -= (int) (moveX / 2);
        this.y -= (int) (moveY / 2);
        particle.x += (int) (moveX / 2);
        particle.y += (int) (moveY / 2);
    }

    @Override
    public void resolveCollision(SpatialElement element) {
        int dx = element.getXCoordinate() - this.getXCoordinate();
        int dy = element.getYCoordinate() - this.getYCoordinate();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (!(distance < this.radius + element.getBoundary())) {
            return;
        }

        this.velY = -this.velY * this.velocityDampener;
        this.velX = -this.velX * this.velocityDampener;
    }

    @Override
    public int getBoundary() {
        return this.radius;
    }

    @Override
    public void setDampner(double velocity) {
        this.velocityDampener = velocity;
    }
}
