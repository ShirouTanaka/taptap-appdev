package com.badlogic.drop.sprites;

import com.badlogic.drop.TapCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    OrthographicCamera cam;

    public Timer() {
        font = new BitmapFont();
        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, TapCore.width/2, TapCore.height/2);

    }
    public void drawTime(SpriteBatch sb) {
        currentTime -= Gdx.graphics.getDeltaTime();
        str = df.format(currentTime);
        font.draw(sb, str, cam.position.x, cam.position.y+160);
    }

    public void resetTime(){
        this.currentTime = 30;
    }

}
