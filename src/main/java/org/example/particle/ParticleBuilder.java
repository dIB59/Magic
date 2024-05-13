package org.example.particle;

public class ParticleBuilder {
    private String color;
    private int mass;
    private int radius;
    private int velX;
    private int velY;
    private int x;
    private int y;

    public ParticleBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public ParticleBuilder setMass(int mass) {
        this.mass = mass;
        return this;
    }

    public ParticleBuilder setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public ParticleBuilder setVelX(int velX) {
        this.velX = velX;
        return this;
    }

    public ParticleBuilder setVelY(int velY) {
        this.velY = velY;
        return this;
    }

    public ParticleBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public ParticleBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public Particle createParticle() {
        return new Particle(color, mass, radius, velX, velY, x, y);
    }
}