package com.badlogic.drop.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Timer {
    SpriteBatch sb;
    private BitmapFont font;
    public float currentTime = 20;
    public CharSequence str;
    public Boolean timeEnded;

    public Timer() {
        font = new BitmapFont();
        sb = new SpriteBatch();
    }
    public void drawTime(SpriteBatch sb) {
        currentTime -= Gdx.graphics.getDeltaTime();
        str = Float.toString(currentTime);
        font.draw(sb, str, 225, 750);
    }

    public void resetTime(){
        this.currentTime = 20;
    }

}
