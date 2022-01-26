package com.badlogic.drop.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.text.DecimalFormat;

public class Timer {
    SpriteBatch sb;
    private BitmapFont font;
    public float currentTime = 20;
    public CharSequence str;
    public Boolean timeEnded;
    private DecimalFormat df = new DecimalFormat("0");
    public Timer() {
        font = new BitmapFont();
        sb = new SpriteBatch();
    }
    public void drawTime(SpriteBatch sb) {
        currentTime -= Gdx.graphics.getDeltaTime();
        str = df.format(currentTime);
        font.draw(sb, str, 225, 734);
    }

    public void resetTime(){
        this.currentTime = 20;
    }

}
