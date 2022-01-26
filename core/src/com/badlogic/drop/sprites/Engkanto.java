package com.badlogic.drop.sprites;

import com.badlogic.drop.TapCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

public class Engkanto {
    //pls add engkanto here
    private Vector3 position;

    private Texture engkanto;
    private Sprite engkantoSprite;

    private static int baseHealth = 900;


    public Engkanto(int x, int y){
        position = new Vector3(x, y, 0);

        engkanto = new Texture(TapCore.pathOptions[1]);
        engkantoSprite = new Sprite(engkanto);
    }


    public void update(float deltaTime) {
        if(position.x > 100){
            position.add(-10, 0, 0);
        }
    }

    public void shake() {
        position.set(120, 400,0);
    }

    public Vector3 getPosition(){
        return position;
    }

    public Sprite getEngkantoSprite(){
        return engkantoSprite;
    }

    public int getBaseHealth(){
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }
}