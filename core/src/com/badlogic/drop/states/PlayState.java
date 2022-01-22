package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PlayState extends State{
    //initialize images here

//    private Texture hero;
//    private Sprite heroSprite;
    private Hero jose;
    private Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        jose = new Hero( 170,80);
        background = new Texture("IMG-0365.png");
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched()){
            jose.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        jose.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
//        sb.draw(hero,(TapCore.width/2) - (hero.getWidth() / 2), (TapCore.height/2) - 200);
//        heroSprite.draw(sb);
        sb.draw(background, 0,0, TapCore.width, TapCore.height);
        sb.draw(jose.getHeroSprite(), jose.getPosition().x, jose.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
