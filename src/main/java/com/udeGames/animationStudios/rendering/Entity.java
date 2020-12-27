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

    public void setRotation(Vector3f rotation) {
        Quaternionf quaternionf = new Quaternionf();
        quaternionf.rotateX(rotation.x);
        quaternionf.rotateY(rotation.y);
        quaternionf.rotateZ(rotation.z);
        this.rotation = quaternionf;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
