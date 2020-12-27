package com.udeGames.animationStudios.rendering;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Entity {
    private Sprite sprite;
    private Vector3f position;
    private Quaternionf rotation;
    private Vector3f scale;

    public Entity(Sprite sprite, Vector3f position, Quaternionf rotation, Vector3f scale) {
        this.sprite = sprite;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void increasePosition(Vector3f position) {
        this.position.x += position.x;
        this.position.y += position.y;
        this.position.z += position.z;
    }

    public void increaseRotation(Vector3f rotation) {
        this.rotation.rotateX(rotation.x);
        this.rotation.rotateY(rotation.y);
        this.rotation.rotateZ(rotation.z);
    }

    public void increaseScale(Vector3f scale) {
        this.scale.x += scale.x;
        this.scale.y += scale.y;
        this.scale.z += scale.z;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
