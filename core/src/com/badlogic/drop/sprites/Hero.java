package com.badlogic.drop.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.sound.sampled.SourceDataLine;

public class Hero {
    //define the base stats here and the various movements
    //code below will define the jumping movement to attack the aswang
    private static final int gravity = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Animation heroAnimation;
    private Texture hero;
    private Sprite heroSprite;
    private Rectangle bounds;
    boolean attacking = false;
    private static double baseMoneyScaler = 1.0; // 100%
    private static double upgrade2Holder = 0.0;
    private static double currentMoneyScaler = baseMoneyScaler;
    private static boolean upgrade2flag = false;
    private static int money = 0;
    //
    private static final int baseDamage = 15;
    private static int currentDamage = baseDamage;
    private Texture texture;


    public Hero(int x, int y){
        position = new Vector3(x, y,0);
        velocity = new Vector3(0, 0, 0);

        //idle
        hero = new Texture("hero.png");
        heroSprite = new Sprite(hero);

        //animation
        texture = new Texture("atk-set.png");
        heroAnimation = new Animation(new TextureRegion(texture), 2,0.5f);
        bounds= new Rectangle(x,y,texture.getWidth()/2, texture.getHeight());

    }

    public static void upgrade1(int value){
        currentDamage = currentDamage + value;
        System.out.println("NEW BASE DAMAGE = " + currentDamage);
    }

    public static void upgrade2(){
        upgrade2Holder += 2.0;
        currentMoneyScaler += upgrade2Holder;
        upgrade2flag = true;
    }

    public static void upgrade3() {
        currentDamage *= 2;
    }

    public void update(float deltaTime){
        heroAnimation.update(deltaTime);

        if(position.y > 80){
            velocity.add(0, gravity,0);
        }

        velocity.scl(deltaTime);
        position.add(0,velocity.y, 0);

        if(position.y < 80){
            position.y = 80;
        }
        velocity.scl(1/deltaTime);
    }

    public void jump(){
        position.set(170, 400,0);
        velocity.y = 100;
    }

    public TextureRegion getTexture(){
        return heroAnimation.getFrame();
    }
    public Rectangle getBounds(){return bounds;}
    public void dispose() {texture.dispose();}

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getHeroSprite() {
        return heroSprite;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public void setCurrentDamage(int currentDamage) {
        this.currentDamage = currentDamage;
    }

    public static void setHeroMoney(int newValue){
        money = newValue;
    }

    public void addMoney(int value){
        money += value;
    }

    public static String getHeroMoney(){
        String value = String.valueOf(money) + " Choc";
        return value;
    }

    public static int getMoneyInt(){
        return money;
    }

    public static double getMoneyScale(){
        return currentMoneyScaler;
    }
    public static void increaseMoneyScaler(){
        currentMoneyScaler += 0.3;
    }
    public static void resetMoneyScaler(){
        if(upgrade2flag == false){ // IF UPGRADE 2 IS NOT YET BOUGHT
            currentMoneyScaler = baseMoneyScaler;
        }else{
            currentMoneyScaler = upgrade2Holder;
        }
    }
}
