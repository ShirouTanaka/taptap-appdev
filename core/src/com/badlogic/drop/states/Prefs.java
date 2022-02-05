package com.badlogic.drop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
    private Preferences prefs;
    private int savedMoney;

    public Prefs(){ // CONSTRUCTOR
        prefs = Gdx.app.getPreferences("My Preferences");
        savedMoney = prefs.getInteger("money"); // JUST GET THE VALUE OF KEY "MONEY"
    }

    // MONEY MANIPULATION METHODS
    public void increaseMoney(int value){
        prefs.putInteger("money", value);
        prefs.flush();
    }

    public int getMoney(){
        return this.savedMoney;
    }
}
