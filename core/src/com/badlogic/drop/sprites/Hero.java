package com.badlogic.drop.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    private Texture hero;
    private Sprite heroSprite;
    private static int money = 0;

    private static final int baseDamage = 10;
    private static int currentDamage = baseDamage;


    public Hero(int x, int y){
        position = new Vector3(x, y,0);
        velocity = new Vector3(0, 0, 0);

        hero = new Texture("hero.png");

        heroSprite = new Sprite(hero);
    }

    public static void upgrade1(int value){
        currentDamage = currentDamage + value;
        System.out.println("NEW BASE DAMAGE = " + currentDamage);
    }

    public static void upgrade3(){
        currentDamage *= 2;
        System.out.println("NEW BASE DAMAGE = " + currentDamage);
    }


    public void update(float deltaTime){
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
}
