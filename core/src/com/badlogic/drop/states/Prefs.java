package com.badlogic.drop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
    private Preferences prefs;
    private int savedMoney;
    private int savedDamage, baseDamage = 15;
    private double savedMoneyScaler, baseMoneyScaler = 1.0;

    public Prefs(){ // CONSTRUCTOR
        prefs = Gdx.app.getPreferences("My Preferences");
        savedMoney = prefs.getInteger("money"); // JUST GET THE VALUE OF KEY "MONEY" = 0

        if(prefs.getInteger("damage") > baseDamage){
            savedDamage = prefs.getInteger("damage"); // JUST GET RAW VALUE OF KEY "DAMAGE"
        }else{ // IF NO UPGRADES HAVE BEEN BOUGHT
            savedDamage = baseDamage;
        }

        if(prefs.getBoolean("bought") == true){
            savedMoneyScaler = prefs.getFloat("scaler");
        }else{
            savedMoneyScaler = baseMoneyScaler;
        }
    }

    // UPGRADES MANIPULATION METHODS
    public void increaseDamage(int value, String flag){
        if(flag == "up1"){ // +10
             savedDamage += value;
        }

        else if(flag == "up3"){ // up3
//            value /= 100; // 20% | 0.2
//            double increase = savedDamage * value;
            savedDamage += (savedDamage*0.2);
            System.out.println("updated DMG: " + savedDamage);
        }
        prefs.putInteger("damage", savedDamage);
        prefs.flush();
    }

    public void increaseMoneyScaler(double value){
        savedMoneyScaler += value;
        prefs.putFloat("scaler", (float) savedMoneyScaler);
        prefs.putBoolean("bought", true);
        prefs.flush();
    }

    public int getDamage(){
        return this.savedDamage;
    }

    public double getScaler(){
        return this.savedMoneyScaler;
    }

    // MONEY MANIPULATION METHODS
    public void increaseMoney(int value){
        savedMoney += value;
        prefs.putInteger("money", savedMoney);
        prefs.flush();
    }

    public void decreaseMoney(int value){
        savedMoney -= value;
        prefs.putInteger("money", savedMoney);
        prefs.flush();
    }

    public int getMoney(){
        return this.savedMoney;
    }
}
