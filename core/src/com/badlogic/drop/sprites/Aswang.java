package com.badlogic.drop.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

public class Aswang {
    //pls add aswang here
    private Vector3 position;

    private Texture aswang;
    private Sprite aswangSprite;

    private int baseHealth = 100;

    public Aswang(int x, int y){
        position = new Vector3(x, y, 0);

        aswang = new Texture("chunkyBoi.png");
        aswangSprite = new Sprite(aswang);
    }

    public Vector3 getPosition(){
        return position;
    }

    public Sprite getAswangSprite(){
        return aswangSprite;
    }

    public int getBaseHealth(){
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }
}
