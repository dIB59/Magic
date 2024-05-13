package org.example;

@FunctionalInterface
public interface SelfCollidable<T extends SelfCollidable<T>> {
    void resolveCollision(T object);
}