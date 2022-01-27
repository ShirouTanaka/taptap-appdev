package com.badlogic.drop.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.text.DecimalFormat;

public class Timer {
    private SpriteBatch sb;
    private BitmapFont font;
    public float currentTime = 30;
    public CharSequence str;
    private DecimalFormat df = new DecimalFormat("0");

    //test


    public Timer() {
        font = new BitmapFont();
        sb = new SpriteBatch();
    }
    public void drawTime(SpriteBatch sb) {
        currentTime -= Gdx.graphics.getDeltaTime();
        str = df.format(currentTime);
        font.draw(sb, str, 240, 734);
    }

    public void resetTime(){
        this.currentTime = 30;
    }

}
