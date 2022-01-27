package com.badlogic.drop.sprites;

import com.badlogic.drop.TapCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

public class Engkanto {
    //pls add engkanto here a
    private Vector3 position;

    private Texture engkanto;
    private Sprite engkantoSprite;
    private Animation slashAnimation;
    private Texture texture;

    private static int baseHealth = 900;
    private Rectangle sbounds;

    public static int i;

    public Engkanto(int x, int y){
        position = new Vector3(x, y, 0);

        engkanto = new Texture(TapCore.pathOptions[i]);
        engkantoSprite = new Sprite(engkanto);

        changeSkin();

        texture = new Texture("slash.png");
        slashAnimation = new Animation(new TextureRegion(texture), 12,0.5f);
        sbounds= new Rectangle(x,y,texture.getWidth()/12, texture.getHeight());
    }


    public void update(float deltaTime) {
        slashAnimation.update(deltaTime);
        if(position.x > 100){
            position.add(-10, 0, 0);
        }
    }

    public TextureRegion getTexture(){
        return slashAnimation.getFrame();
    }
    public Rectangle getBounds(){return sbounds;}
    public void dispose() {
        texture.dispose();
    }

    public void skinDispose(){
        engkanto.dispose();
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

    public static String getEngkantoHealth(int baseHealth){
        String value = String.valueOf("Engkanto Health: " + baseHealth);
        return value;
    }
    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void changeSkin(){
        skinDispose();

        //change the skin
        if (i < 5) {
            engkanto = new Texture(TapCore.pathOptions[i++]);
        }
        else {
            i = 0;
            engkanto = new Texture(TapCore.pathOptions[i]);
        }
        engkantoSprite = new Sprite(engkanto);
    }
}